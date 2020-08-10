package com.quiz.frontend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.quiz.frontend.model.Quiz.Quiz;

@Controller
public class homeController {

    @GetMapping("/home")
    public String home(final Model model){
        final String url = "http://localhost:8081/api/quiz/all";
        final RestTemplate restTemplate = new RestTemplate();

        final Quiz[] quiz = restTemplate.getForObject(url, Quiz[].class);
        model.addAttribute("quizList", quiz);
        return "home";
    }  
}
