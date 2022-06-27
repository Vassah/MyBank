package com.Vassah.MyBank.Services;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.Vassah.MyBank.Model.Authority;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Repositories.AuthorityRepository;
import com.Vassah.MyBank.Repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Service
@Component
@AllArgsConstructor
public class UserManager implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private JavaMailSender mailSender;

    private final UserRepository userRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    private AuthorityRepository rolesRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByPhoneNumber(username);
        if (user == null) {
            String msg = "User" + username + "not found";
            logger.info(msg);
            throw new UsernameNotFoundException(msg);
        }

        return user;
    }

    public User getUser(String username) throws UsernameNotFoundException {
        var user = userRepo.findByPhoneNumber(username);
        if (user == null) {
            String msg = "User" + username + "not found";
            logger.info(msg);
            throw new UsernameNotFoundException(msg);
        }

        return user;
    }

    @Autowired
    public UserManager(AuthorityRepository _rrepo, UserRepository _urepo, BCryptPasswordEncoder _encoder) {
        rolesRepo = _rrepo;
        userRepo = _urepo;
        passwordEncoder = _encoder;
        seedRoles();

    }

    private void seedRoles() {
        if (!rolesRepo.findByName("User_role").isPresent()) {
            rolesRepo.save(new Authority(1L, "User_role"));
        }
        if (!rolesRepo.findByName("Admin_role").isPresent()) {
            rolesRepo.save(new Authority(2L, "Admin_role"));
            User admin = userRepo.findById(1L).isPresent() ? userRepo.findById(1L).get() : new User();
            admin.setPhoneNumber("+79000000000");
            admin.setPasswordHash(passwordEncoder.encode("6815255"));
            admin.setFirstName("Alexandr");
            admin.setLastName("Vasiliy");
            admin.setEmail("ad.akantev@phystech.edu");
            admin.setEnabled(true);
            admin.setAuthorities((Collections.singleton(new Authority(2L, "Admin_role"))));
            userRepo.save(admin);
        }
    }

    public void sendPhoneCode(String phoneNumber) {

    }

    public boolean checkPhoneCode(String code) {
        return true;
    }

    public void updateUserProfile(User user) {
        userRepo.save(user);
    }

    public boolean registerUser(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        User userFromDB = userRepo.findByPhoneNumber(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        userFromDB = userRepo.findByEmail(user.getEmail());
        if (userFromDB != null) {
            return false;
        }
        user.setAuthorities(Collections.singleton(new Authority(1L, "User_role")));
        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        user.setVerificationCode(RandomString.make(64));
        userRepo.save(user);
        sendVerificationEmail(user, siteURL);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }

    public void SendCodeAgain(String email, String siteURL)
    throws MessagingException, UnsupportedEncodingException {
        var user = userRepo.findByEmail(email);
        sendVerificationEmail(user, siteURL);
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "mybankapplicationjava@mail.ru";
        String senderName = "My Bank";
        String subject = "Подтверждение регистрации";
        String content = "Dear [[name]],<br>"
                + "Пожалуйста, перейдите по ссылке ниже чтобы подтвердить регистрацию:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">ПОДТВЕРЖДЕНИЕ</a></h3>"
                + "[[URL]] <br>"
                + "Благодарим вас,<br>"
                + "команда My Bank";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.fullName());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    public boolean verify(String verificationCode) {
        User user = userRepo.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            return false;
        }
        user.setVerificationCode(null);
        user.setEnabled(true);
        userRepo.save(user);
        return true;

    }
}
