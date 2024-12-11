package com.notes.controllers;

import com.notes.entities.dto.LoginFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/dashboard")
    public String displayDashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }
}
