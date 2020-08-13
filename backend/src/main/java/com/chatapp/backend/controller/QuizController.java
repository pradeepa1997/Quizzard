package com.chatapp.backend.controller;

import com.chatapp.backend.repository.Quizrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

import com.chatapp.backend.model.Quiz;

@RestController
@RequestMapping(value = "/api/quiz")
public class QuizController {
    @Autowired
    Quizrepo quizrepo;

    @GetMapping(value = "/all")
    public List<Quiz> getall() {
        return quizrepo.findAll();
    }
    @PostMapping(value = "/add")
    public Integer add(@RequestBody final Quiz quiz) {
        System.out.println(quiz.getCreatorID()+quiz.getQuizCategory()+quiz.getQuizID()+quiz.getQuizName());
        Quiz saved=quizrepo.save(quiz);
        System.out.println(saved.getQuizID());
        return saved.getQuizID();
    }
    @GetMapping(value = "/{id}")
    public Optional<Quiz> getByID(@PathVariable final Integer id) {
        return quizrepo.findById(id);
    }
}