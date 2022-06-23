package com.Vassah.MyBank.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AccountController {

    @GetMapping("/admin")
    public String Admin()
    {
        return "admin";
    }

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal User user, Model model)
    {
        model.addAttribute("user", user);
        return "profile";
    }
    
}
