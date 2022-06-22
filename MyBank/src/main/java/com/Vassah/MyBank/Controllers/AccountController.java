package com.Vassah.MyBank.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("admin")
    public String Admin()
    {
        return "admin";
    }
    
}
