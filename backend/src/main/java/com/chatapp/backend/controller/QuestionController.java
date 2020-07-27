package com.chatapp.backend.controller;

import java.util.List;

import com.chatapp.backend.model.Question;
import com.chatapp.backend.repository.Questionrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/question")
public class QuestionController {
    @Autowired
    Questionrepo questionrepo;

    @GetMapping(value = "/all")
    public List<Question> getall() {
        return questionrepo.findAll();
    }
    @PostMapping(value = "/add")
    public Integer add(@RequestBody final Question question) {
        Question saved=questionrepo.save(question);
        System.out.println(saved.getQuizID());
        return saved.getQuizID();
    }

    
}