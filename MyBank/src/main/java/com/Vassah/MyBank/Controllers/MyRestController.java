package com.Vassah.MyBank.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Services.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MyRestController{

    @Autowired UserManager userManager;
    @PostMapping(value="/reg")
    public ResponseEntity<?> postMethodName(@RequestBody User user) {
        userManager.registerUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    
}
