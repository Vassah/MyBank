package com.Vassah.MyBank.Controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/profile")
    public String Lk()
    {return "personal_account";}

    @GetMapping(value={"/", "/index"})
    public String Index()
    {
        return "index";
    }

    @GetMapping("/403")
    public String error403()
    {
        return "Error/403";
    }

    @GetMapping("/404")
    public String error404()
    {
        return "Error/404";
    }




}