package com.chatapp.backend.model;


import java.util.ArrayList;
import java.util.List;

public class Questions {
    private Quiz quiz;
    private List<Question> questions=new ArrayList<Question>();  
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public Quiz getQuiz() {
        return quiz;
    }
    
}