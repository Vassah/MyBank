package com.Vassah.MyBank.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Vassah.MyBank.model.Authority;
import com.Vassah.MyBank.model.User;
import com.Vassah.MyBank.repositories.AuthorityRepository;
import com.Vassah.MyBank.repositories.UserRepository;

@Service
@Component
public class PopulateDBService {

    @Autowired
    private AuthorityRepository authRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void populate() {
        if (!authRepo.findByName("User_role").isPresent()) {
            authRepo.save(new Authority(1L, "User_role"));
        }
        if (!authRepo.findByName("Admin_role").isPresent()) {
            authRepo.save(new Authority(2L, "Admin_role"));
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

}
