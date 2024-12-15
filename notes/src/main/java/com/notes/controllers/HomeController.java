package com.notes.controllers;

import com.notes.entities.dto.LoginFormDTO;
import com.notes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/dashboard")
    public String displayDashboard(Model model) {
        model.addAttribute("h3", "Dashboard");
        return "dashboard";
    }

}
