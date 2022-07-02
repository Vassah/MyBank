package com.vassah.myBank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCconfig implements WebMvcConfigurer {
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/" ).setViewName("index");
        registry.addViewController("/about-us").setViewName("we/about-us");
        registry.addViewController("/404").setViewName("error/404");
        registry.addViewController("/403").setViewName("error/403");
        registry.addViewController("/ConfirmEmail").setViewName("ConfirmEmail");
        registry.addViewController("/ConfirmEmail/success").setViewName("EmailConfirmed");
        registry.addViewController("/ConfirmEmail/fail").setViewName("EmailConfirmFailed");
        registry.addViewController("/contact-us").setViewName("we/contact-us");
        registry.addViewController("/our-services").setViewName("we/our-services");


    }
}
