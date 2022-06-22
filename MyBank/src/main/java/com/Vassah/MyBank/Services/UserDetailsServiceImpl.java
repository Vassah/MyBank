package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException{
        var user = userRepo.findByPhoneNumber(username);
        if (user == null)
        {
            String msg = "User" + username +"not found";
            logger.info(msg);
            throw new UsernameNotFoundException(msg);
        }

       return user;
    }
    
}
