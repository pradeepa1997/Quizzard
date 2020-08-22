package com.chatapp.backend.controller;

import com.chatapp.backend.model.Quiztry;
import com.chatapp.backend.repository.Quiztryrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/quiztry")
public class QuiztryController {
    @Autowired
    Quiztryrepo quiztryrepo;


    // @PostMapping(value = "/add")
    // public String addNewUser(@RequestBody final User user) {
    //     System.out.println(user.getEmail());
    //     usersrepo.save(user);
    //     return "User added"; 
    // }
    @PostMapping(value = "/add")
    public String add(@RequestBody final Quiztry quiztry) {
        System.out.println("\n\n\n\n\\n\n\njjjjjj");
        System.out.println(quiztry.getMarks());
        // System.out.println(quiztry.getQuizID()+quiztry.getUserID()+quiztry.getMarks()+quiztry.getId());
        quiztryrepo.save(quiztry);
        return "Done";
    }
    // @PostMapping(value = "/add")
    // public Integer add(@RequestBody final Quiz quiz) {
    //     System.out.println(quiz.getCreatorID()+quiz.getQuizCategory()+quiz.getQuizID()+quiz.getQuizName());
    //     Quiz saved=quizrepo.save(quiz);
    //     System.out.println(saved.getQuizID());
    //     return saved.getQuizID();
    // }

}