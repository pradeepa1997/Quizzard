package com.quiz.frontend.controller;

import java.lang.ProcessBuilder.Redirect;

import com.quiz.frontend.model.Quiz.Quiz;
import com.quiz.frontend.model.User.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.ui.Model;

@Controller
public class adminController {

    @GetMapping("/admin/users")
    public String home(final Model model){
        final String userurl = "http://localhost:8081/api/users/all";
        final RestTemplate restTemplate = new RestTemplate();

        final User[] userObject = restTemplate.getForObject(userurl, User[].class);
        System.out.println("\n\n\n");
        System.out.println(userObject);

        System.out.println("\n\n\n");

        final String quizurl = "http://localhost:8081/api/quiz/all";

        final Quiz[] quizObject = restTemplate.getForObject(quizurl, Quiz[].class);
        System.out.println("\n\n\n");
        System.out.println(quizObject);

        System.out.println("\n\n\n");

        model.addAttribute("userList", userObject);
        model.addAttribute("quizList", quizObject);
        return "adminpanel";
    }
    
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        final String delurl = "http://localhost:8081/api/users/delete/" + id;
        final RestTemplate restTemplate = new RestTemplate();

        System.out.println("\n\n\n");
        System.out.println(id);

        System.out.println("\n\n\n");
        restTemplate.delete(delurl);
        // restTemplate.delete(, id);
        return ("redirect:/admin/users");
        
    }
    
}