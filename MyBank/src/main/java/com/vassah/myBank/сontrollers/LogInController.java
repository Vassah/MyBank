package com.vassah.myBank.сontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.vassah.myBank.model.User;

@Controller
public class LogInController {

    @GetMapping("/user/login")
    public String logInAccount(Model model)
    {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/user/login")
    public String logInAccount()
    {
        return "redirect:/profile";
    }

}