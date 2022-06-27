package com.Vassah.MyBank.Controllers;

import com.Vassah.MyBank.Model.User;
<<<<<<< HEAD
//import com.Vassah.MyBank.Services.UserManager;
import com.Vassah.MyBank.Services.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
=======
import com.Vassah.MyBank.Services.UserManager;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
>>>>>>> 16971818a06956a5320e750afe4e973545bc02c8
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

=======
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> 16971818a06956a5320e750afe4e973545bc02c8

@Controller
public class RegistrationController {

    @Autowired
    private UserManager userManager;
<<<<<<< HEAD

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String RegisterUser(Model model)
    {
        model.addAttribute("user", new User());
=======

    @GetMapping("/registration")
    public String Registration(Model model) {
        model.addAttribute("userForm", new User());
>>>>>>> 16971818a06956a5320e750afe4e973545bc02c8
        return "registration";
    }
    
    @PostMapping("/registration")
    public String RegisterNewUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                    @RequestParam("password") String password)
    {
        User user = new User();

<<<<<<< HEAD
        user.setFirstName(name);
        user.setLastName(surname);
        user.setPasswordHash(passwordEncoder.encode(password));
        userManager.RegisterUser(user);

        return "redirect:/index";
    }



 

    @GetMapping("registration/phone")
    public String PhoneAdmit()
    {
        return "registration/phone";
    }



    

=======
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

    @GetMapping("/sendagain")
    public String sendEmailAgain(@RequestParam("email") String email, HttpServletRequest request)
                throws UnsupportedEncodingException, MessagingException {
        userManager.SendCodeAgain(email, request.getServletPath());
        return "redirect:/ConfirmEmail";
    }
>>>>>>> 16971818a06956a5320e750afe4e973545bc02c8

}
