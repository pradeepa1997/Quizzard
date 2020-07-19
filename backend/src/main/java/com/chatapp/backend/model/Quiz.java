package com.chatapp.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quiz {
    @Id
    @GeneratedValue
    @Column(name="quizID") 
    private Integer quizID;
    @Column(name="quizName")
    private String quizName;
    @Column(name="creatorID")
    private Integer creatorID;
    


    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }
    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    public Integer getCreatorID() {
        return creatorID;
    }
    public Integer getQuizID() {
        return quizID;
    }
    public String getQuizName() {
        return quizName;
    }
}