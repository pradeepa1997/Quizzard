package com.quiz.frontend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import com.quiz.frontend.model.Quiz.Quiz;

@Controller
public class homeController {

    @GetMapping("/home")
    public String home(final Model model){
        final String url = "http://localhost:8081/api/quiz/all";
        final RestTemplate restTemplate = new RestTemplate();

        final Quiz[] quizes = restTemplate.getForObject(url, Quiz[].class);
        List<Quiz> Maths = new ArrayList<Quiz>();
        List<Quiz> Science = new ArrayList<Quiz>();
        List<Quiz> Arts = new ArrayList<Quiz>();
        List<Quiz> IT = new ArrayList<Quiz>();
        List<Quiz> Other = new ArrayList<Quiz>();
        List<String> category = new ArrayList<String>();
        category.add("Maths");
        category.add("Science");
        category.add("Arts");
        category.add("IT");
        category.add("Other");

        for (final Quiz quiz : quizes) {
            if(quiz.getQuizCategory().equals("Maths")){    
                Maths.add(quiz);
            }else if(quiz.getQuizCategory().equals("Science")){    
                Science.add(quiz);
            }else if(quiz.getQuizCategory().equals("Arts")){    
                Arts.add(quiz);
            }else if(quiz.getQuizCategory().equals("IT")){    
                IT.add(quiz);
            }else{    
                Other.add(quiz);
            }
        }
        model.addAttribute("Maths", Maths);
        model.addAttribute("Science", Science);
        model.addAttribute("Arts", Arts);
        model.addAttribute("IT", IT);
        model.addAttribute("Other", Other);
        model.addAttribute("category", category);
        return "home";
    }  
}
