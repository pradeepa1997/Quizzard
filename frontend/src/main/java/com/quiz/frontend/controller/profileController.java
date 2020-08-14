package com.quiz.frontend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.frontend.model.JWTData;
import com.quiz.frontend.model.User.User;

@Controller
public class profileController {
    
    JWTData jwttoken=new JWTData();
    
    @GetMapping(value = "profile/{id}")
    public String userProfile(@PathVariable final Integer id, final Model model) {
        final String url = "http://localhost:8081/api/users/" + id;
        final RestTemplate restTemplate = new RestTemplate();


        final Object[] obj = restTemplate.getForObject(url, Object[].class );
        
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(obj[0], User.class);

        model.addAttribute("user", user);
        model.addAttribute("quiz", obj[1]);
        model.addAttribute("quizCount", obj[2]);
        model.addAttribute("try", obj[3]);
        model.addAttribute("tryCount", obj[4]);

        if(!jwttoken.isLog()){
            return ("redirect:/login");
        }
        model.addAttribute("username",jwttoken.getUserName());
        model.addAttribute("userID",jwttoken.getUserId());
        return "profile";   
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute final User userData, final Model model) {
        final String url = "http://localhost:8081/api/users/update";
        
        final RestTemplate restTemplate = new RestTemplate();
        // restTemplate.put(url, request);
        System.out.println(userData.getEmail()+ userData.getPassword() + userData.getToken() + userData.getUserID() + userData.getUserName() + userData.getUserType()+ "\n\n\n\n\n\n\n\n\n\n\n\n");
        // restTemplate.put(url, user0Data);
        restTemplate.put(url,userData);

        // System.out.println(quizid+ "\n\n\n\n\n\n\n\n\n\n\n\n");

        return ( "redirect:/profile/" + userData.getUserID());
    }
}