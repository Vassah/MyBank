package com.Vassah.MyBank.Controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String Index()
    {
        return "index";
    }

    @GetMapping("/first_page")
    public String FirstPage(){
        return "first_page";
    }

}