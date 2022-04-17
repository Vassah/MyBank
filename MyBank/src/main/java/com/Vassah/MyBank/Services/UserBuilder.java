package com.Vassah.MyBank.Services;

import com.Vassah.MyBank.Exceptions.UserAlreadyExistException;
import com.Vassah.MyBank.Repositories.UserRepository;
import com.Vassah.MyBank.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserBuilder {

    @Autowired
    private UserRepository userRepo;


    public User registration(User user) throws UserAlreadyExistException{
        if (userRepo.findById(user.getId())!=null){
            throw new UserAlreadyExistException("Пользователь с таким именем существует");
        }
        return userRepo.save(user);
    }
}
