package com.chatapp.backend.controller;

import java.util.List;

import com.chatapp.backend.model.QuizCategory;
import com.chatapp.backend.repository.QuizCategoryrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/quizCatogory")
public class QuizCatogory {
    
    @Autowired
    QuizCategoryrepo quizCategoryrepo;

    @GetMapping(value = "/all")
    public List<QuizCategory> getall() {
        return quizCategoryrepo.findAll();
    }
    
}