package com.Vassah.MyBank.Services;

import java.util.Collections;

import com.Vassah.MyBank.Model.Role;
import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Repositories.RolesRepository;
import com.Vassah.MyBank.Repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@Component
@AllArgsConstructor
public class UserManager implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserManager.class);

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

    public User getUser(String username) throws UsernameNotFoundException{
        var user = userRepo.findByPhoneNumber(username);
        if (user == null) {
            String msg = "User" + username + "not found";
            logger.info(msg);
            throw new UsernameNotFoundException(msg);
        }

        return user;
    }

    private final UserRepository userRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    private RolesRepository rolesRepo;

    @Autowired
    public UserManager(RolesRepository _rrepo, UserRepository _urepo, BCryptPasswordEncoder _encoder) {
        rolesRepo = _rrepo;
        userRepo = _urepo;
        passwordEncoder = _encoder;
        seedRoles();

    }

    private void seedRoles() {
        if (!rolesRepo.findById(1L).isPresent()) {
            rolesRepo.save(new Role(1L, "User_role"));
        }

        if (!rolesRepo.findById(2L).isPresent()) {
            rolesRepo.save(new Role(2L, "Admin_role"));
            User admin = userRepo.findById(1L).isPresent() ? userRepo.findById(1L).get() : new User();
            admin.setPhoneNumber("+70000000000");
            admin.setPasswordHash(passwordEncoder.encode("6815255"));
            admin.setRoles((Collections.singleton(new Role(2L, "Admin_role"))));
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

    public boolean registerUser(User user) {
        User userFromDB = userRepo.findByPhoneNumber(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        userFromDB = userRepo.findByEmail(user.getEmail());
        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "User_role")));
        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepo.findById(userId).isPresent()) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }
}
