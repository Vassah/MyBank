package com.Vassah.MyBank.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@PreAuthorize("hasAuthority('Admin_role')")
@Controller
public class AdminController {
    @GetMapping("/admin")
    public String Admin() {
        return "admin/index";
    }
}