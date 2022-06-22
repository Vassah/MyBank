package com.Vassah.MyBank.Controllers;

import com.Vassah.MyBank.Model.User;
//import com.Vassah.MyBank.Services.UserManager;
import com.Vassah.MyBank.Services.UserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RegistrationController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registration/new")
    public String RegisterUser(Model model)
    {
        model.addAttribute("user", new User());
        return "registration";
    }
    
    @PostMapping("/registration")
    public String RegisterNewUser(@RequestParam("name") String name, @RequestParam("surname") String surname,
                                    @RequestParam("password") String password)
    {
        User user = new User();

        user.setFirstName(name);
        user.setLastName(surname);
        user.setPasswordHash(passwordEncoder.encode(password));
        userManager.RegisterUser(user);

        return "index";
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



