package com.Vassah.MyBank.Controllers;

//import com.Vassah.MyBank.Model.User;
//import com.Vassah.MyBank.Services.UserManager;

import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class RegistrationController {

    //@Autowired
    //private UserManager usserManger;

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
    
    @GetMapping("login")
    public String Login()
    {
        return "login";
    }

}



