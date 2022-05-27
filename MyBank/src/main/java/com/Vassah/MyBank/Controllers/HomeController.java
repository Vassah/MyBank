package com.Vassah.MyBank.Controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/layout")
    public String Layout()
    {return "layout";}

    @GetMapping("/")
    public String Index()
    {
        return "index";
    }


}