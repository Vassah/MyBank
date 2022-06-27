package com.Vassah.MyBank.Controllers;

import com.Vassah.MyBank.Model.User;
import com.Vassah.MyBank.Services.UserManager;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserManager userManager;

    @GetMapping("/registration")
    public String Registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String RegisterUser(@ModelAttribute("userForm") @Validated User userForm, HttpServletRequest request,
            BindingResult bindingResult, Model model) throws UnsupportedEncodingException, MessagingException {
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirmHash())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/registration";
        }
        if (!userManager.registerUser(userForm, request.getServletPath())) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/registration";
        }
        return "redirect:/ConfirmEmail";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code) {
        if (userManager.verify(code)) {
            return "redirect:/EmailConfirmed";
        } else {
            return "redirect:/EmailConfirmFailed";
        }
    }

}
