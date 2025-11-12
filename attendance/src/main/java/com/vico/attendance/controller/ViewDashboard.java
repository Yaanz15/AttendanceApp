package com.vico.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewDashboard {

    @GetMapping("/dashboard")
    public String showHomePage() {
        // This will load src/main/resources/templates/index.html (if using Thymeleaf)
        return "index";
    }

}
