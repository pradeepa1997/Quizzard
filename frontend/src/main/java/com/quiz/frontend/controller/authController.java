package com.quiz.frontend.controller;

import java.net.URI;

import com.quiz.frontend.model.User.RegisterUser;
import com.quiz.frontend.model.User.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class authController {
    @RequestMapping(value = "/login")
    public String login(final Model model) {
        return "auth/login";
    }

    @GetMapping(value = "/register")
    public String register(final Model model) {
        model.addAttribute("user", new RegisterUser());
        return "auth/register";
    }

    // @RequestMapping("/adNewUser")
    // public String registerUser(RegisterUser user, BindingResult bindingResult) {
    //     RestTemplate restTemplate = new RestTemplate();

    //     String url = "http://localhost:8081/auth/register";

    //     User newUser = new User();
    //     newUser.setEmail(user.getEmail());
    //     newUser.setUserName(user.getUserName());
    //     newUser.setPassword(user.getPassword());

    //     String quizid = restTemplate.postForObject(url, newUser, String.class);
    //     System.out.println(quizid);
    //     return "done";
    // }

    @RequestMapping(value = "/forgotPassword")
    public String forgotPassword(final Model model) {
        return "auth/forgotPassword";
    }

    @RequestMapping(value = "/resetPassword")
    public String resetPassword(final Model model) {
        return "auth/resetPassword";
    }
}