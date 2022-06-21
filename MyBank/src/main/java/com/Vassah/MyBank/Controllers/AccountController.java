package com.Vassah.MyBank.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.Vassah.MyBank.Model.*;


@Controller
public class AccountController {
    User User;
    public AccountController()
    {
        
    }

    @GetMapping("/user/login")
    public String LogInAccount()
    {
        return "login";
    }


    
}
