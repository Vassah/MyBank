package com.Vassah.MyBank.Controllers;

import com.Vassah.MyBank.Repositories.UserRepository;
import com.Vassah.MyBank.Services.UserBuilder;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepo;

    public RegistrationController(UserRepository _userRepoo)
    {
        userRepo = _userRepoo;
    }

    @GetMapping("/registration")
    public String RegisterUser()
    {
        return "registration";
    }


    @PostMapping(value="/registration")
    public String RegisterUser(@RequestBody UserBuilder Name) {
        return "registration/phone";
    }

    @GetMapping("registration/phone")
    public String PhoneAdmit()
    {
        return "registration/phone";
    }


    
    

}



