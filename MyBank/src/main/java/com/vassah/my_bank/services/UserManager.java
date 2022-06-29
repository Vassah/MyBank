package com.vassah.my_bank.services;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vassah.my_bank.model.Authority;
import com.vassah.my_bank.model.User;
import com.vassah.my_bank.repositories.AuthorityRepository;
import com.vassah.my_bank.repositories.UserRepository;

import net.bytebuddy.utility.RandomString;

@Service
@Component
public class UserManager implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserManager.class);

    private final UserRepository userRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    private final EmailService emailService;

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

    public UserManager(AuthorityRepository authrepo, UserRepository urepo, BCryptPasswordEncoder encoder,
            EmailService eService, PopulateDBService dbService) {
        userRepo = urepo;
        passwordEncoder = encoder;
        emailService = eService;
        dbService.populate();

    }

    public void updateUserProfile(User user) {
        userRepo.save(user);
    }

    public boolean deleteUser(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
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
        emailService.sendVerificationEmail(user, siteURL);
        return true;
    }

    public void sendCodeAgain(String email, String siteURL) throws MessagingException, UnsupportedEncodingException {
        var user = userRepo.findByEmail(email);
        emailService.sendVerificationEmail(user, siteURL);
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
