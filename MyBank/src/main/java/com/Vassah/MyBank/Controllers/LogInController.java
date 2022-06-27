package com.Vassah.MyBank.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.Vassah.MyBank.Model.User;

@Controller
public class LogInController {

    @GetMapping("/user/login")
    public String LogInAccount(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/user/login")
    public String LogInAccount()
    {
        return "redirect:/profile";
    }

}