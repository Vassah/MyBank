package com.vassah.myBank.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vassah.myBank.model.Authority;
import com.vassah.myBank.model.User;
import com.vassah.myBank.repositories.AuthorityRepository;
import com.vassah.myBank.repositories.UserRepository;

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
            admin.setAuthorities(Collections.singleton(new Authority(2L, "Admin_role")));
            userRepo.save(admin);
        }

        for(int i=1; i < 10; i++)
            {
                if (userRepo.findByPhoneNumber("+7900000000" + String.valueOf(i))==null)
                userRepo.save(getUser(i));
            }
            
    }

    private User getUser(int i)
    {
        User user = new User();
        user.setEmail(String.join(String.valueOf(i),"example","@example.ru"));
        user.setPhoneNumber("+7900000000" + String.valueOf(i));
        user.setPasswordHash(passwordEncoder.encode("12345"));
        user.setFirstName("Ivan" + String.valueOf(i));
        user.setLastName("Ivanov" + String.valueOf(i));
        user.setEnabled(true);
        user.setAuthorities(Collections.singleton(new Authority(1L, "User_role")));
        return user;
    }

}
