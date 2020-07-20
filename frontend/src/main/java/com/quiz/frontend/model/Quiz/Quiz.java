package com.quiz.frontend.model.Quiz;

import java.io.Serializable;

public class Quiz implements Serializable {
    private Integer creatorID;
    private String quizCategory;
    private String quizName;

    public Quiz(){
        super();
    }
    public Quiz(Integer creator,String cat,String name){
        super();
        this.creatorID=creator;
        this.quizCategory=cat;
        this.quizName=name;
    }
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    public void setQuizCategory(String quizeCategory) {
        this.quizCategory = quizeCategory;
    }
    public String getQuizName() {
        return quizName;
    }
    public String getQuizCategory() {
        return quizCategory;
    }
    public Integer getCreatorID() {
        return creatorID;
    }
    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }
    
}