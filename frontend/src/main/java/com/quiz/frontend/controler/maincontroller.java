package com.quiz.frontend.controler;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class maincontroller {

    @RequestMapping(value = "/index")
    public String index(final Model model) {
        final String name = "pradeepa Sandaruwan";
        // data ("pradeepa");
        // data.add("Sandaruwan");
        model.addAttribute("name", name);
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
}