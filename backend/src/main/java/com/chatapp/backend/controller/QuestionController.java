package com.chatapp.backend.controller;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import com.chatapp.backend.model.Question;
import com.chatapp.backend.repository.Questionrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


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

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable final Integer id) {
        questionrepo.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public Optional<Question> getById(@PathVariable final Integer id) {
        return questionrepo.findById(id);
    }

    @GetMapping(value = "/getByQuizId/{quizId}")
    public List<Question> getByQuizID(@PathVariable final Integer quizId) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nhi");
        List<Question> temp=questionrepo.findByQuizID(quizId);
        return temp;
    }
    // @PostMapping(value = "/getByQuizId/{quizId}")
    // public Integer getAllofQuiz(@RequestBody final Question question) {
    //     return questionrepo.findByQuizID();
       
    // }

    
}