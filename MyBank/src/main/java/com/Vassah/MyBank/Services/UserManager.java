package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@Component
@AllArgsConstructor
public class UserManager {
    @Autowired
    private final UserRepository userRepo;

    public void SendPhoneCode(String phoneNumber)
    {

        if (userRepo.findByPhoneNumber(phoneNumber)!=null)
        {
            
        }


    }

    public boolean CheckPhoneCode(String code)
    {
        return true;
    }

    public void RegisterUser(User user)
    {
        userRepo.save(user);
    }

    public void UpdateUserProfile(User user)
    {
        userRepo.save(user);
    }
}
