package com.quiz.frontend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class homeController {
    @GetMapping(value = "/profile")
    public String profile(final Model model) {
        // model.addAttribute("quiz", new QuizPost());
        return "profile";   
    }
}
