package com.chatapp.backend.controller;

import com.chatapp.backend.repository.Questionrepo;
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

import com.chatapp.backend.model.Question;
import com.chatapp.backend.model.Questions;
import com.chatapp.backend.model.Quiz;

@RestController
@RequestMapping(value = "/api/quiz")
public class QuizController {
    @Autowired
    Quizrepo quizrepo;

    @Autowired
    Questionrepo questionrepo;

    @GetMapping(value = "/all")
    public List<Quiz> getall() {
        List<Quiz> temp=quizrepo.findAll();
        for (final Quiz element : temp){
            System.out.println(element.getQuizID());
        }
        return temp;
    }
    @PostMapping(value = "/add")
    public Integer add(@RequestBody final Quiz quiz) {
        System.out.println("\n\n\n\n\nPAA PIMPIYA\n\n\n\n\n\n");
        System.out.println(quiz.getCreatorID()+quiz.getQuizCategory()+quiz.getQuizID()+quiz.getQuizName());
        Quiz saved=quizrepo.save(quiz);
        System.out.println(saved.getQuizID());
        return saved.getQuizID();
    }
    @GetMapping(value = "/{id}")
    public Questions getByID(@PathVariable final Integer id) {

        try{
            List<Question> questions=questionrepo.findByQuizID(id);
            Questions result=new Questions();
            Optional<Quiz> quiz=quizrepo.findById(id);
            
            result.setQuiz(quiz.get());
            result.setQuestions(questions);
            return result;
        }
        catch(Exception e){
            return null;
        }
        

    }
    
}