package com.Vassah.MyBank.Controllers;

import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Services.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    @Autowired
    private UserManager userManager;

    @GetMapping("/registration")
    public String Registration(Model model)
    {
        model.addAttribute("userForm", new User());
        return "registration";
    }
    
    @PostMapping("/registration")
    public String RegisterUser(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "/registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirmHash())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/registration";
        }
        if (!userManager.registerUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/registration";
        }

        return "redirect:/ConfirmEmail";
    }

    @GetMapping("/ConfirmEmail")
    public String ConfirmEmail()
    {
        return "ConfirmEmail";
    }

    @GetMapping("/registration/phone")
    public String PhoneAdmit()
    {
        return "registration/phone";
    }

}



