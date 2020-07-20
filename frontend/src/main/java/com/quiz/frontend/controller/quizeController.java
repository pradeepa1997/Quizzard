package com.quiz.frontend.controller;

import com.quiz.frontend.model.Quiz.Quiz;
import com.quiz.frontend.model.Quiz.QuizPost;
import com.quiz.frontend.model.Quiz.QuizeGet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class quizeController {
    
    @GetMapping(value = "/addquiz")
    public String addQuiz(final Model model) {
        // getEmployees();
        model.addAttribute("quiz", new QuizPost());
        return "addquiz";   
    }

    @PostMapping("/addquiz")
    public String addQuizSubmit(@ModelAttribute QuizPost quizPost){
        System.out.println(quizPost.getQuizName());
        final String uri = "http://localhost:8081/api/quiz/add";
        RestTemplate restTemplate = new RestTemplate();
        Quiz quiz=new Quiz(1,quizPost.getQuizeCategory(),quizPost.getQuizName());

        String temp=restTemplate.postForObject(uri,quiz ,String.class);
        System.out.println(temp);
        return "home";
    }


    private static void getEmployees(){
        // getForObject(url, Quiz[].class)
        final String uri = "http://localhost:8081/api/quiz/all";
        RestTemplate restTemplate = new RestTemplate();
        QuizeGet[] result = restTemplate.getForObject(uri, QuizeGet[].class);
        // System.out.println(result[0].getQuizName());
    }
    // private static void getEmployees(){
    //     // getForObject(url, Quiz[].class)
    //     final String uri = "http://localhost:8081/api/quiz/all";
    //     RestTemplate restTemplate = new RestTemplate();
    //     QuizeGet[] result = restTemplate.getForObject(uri, QuizeGet[].class);
    //     System.out.println(result[0].getQuizName());
    // }
   
}