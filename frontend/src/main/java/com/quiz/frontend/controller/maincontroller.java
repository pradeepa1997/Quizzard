package com.quiz.frontend.controller;



// import com.quiz.frontend.model.;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class maincontroller {

    @RequestMapping(value = "/")
    public String index(final Model model) {
        return "index";
    }

    @RequestMapping(value = "/home")
    public String home(final Model model) {
        return "home";
    }

    @RequestMapping(value = "/login")
    public String login(final Model model) {
        return "login";
    }

    @RequestMapping(value = "/quiz")
    public String quiz(final Model model) {
        return "quiz";
    }

    @RequestMapping(value = "/admin")
    public String admin(final Model model) {
        return "adminpanel";
    }
}