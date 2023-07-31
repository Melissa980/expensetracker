package com.melissa.ExpenseTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showHomePage() {
        return "index"; // Assuming there's an "index.html" view template as the homepage
    }
}
