package com.chatapp.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Quiztry {
    @Id
    @GeneratedValue
    @Column(name="id") 
    private Integer id;
    @Column(name="userID") 
    private Integer userID;
    @Column(name="quizID")
    private Integer quizID;
    @Column(name="marks")
    private Integer marks;
    


    public Integer getId() {
        return id;
    }
    public Integer getMarks() {
        return marks;
    }
    public Integer getQuizID() {
        return quizID;
    }
    public Integer getUserID() {
        return userID;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setMarks(Integer marks) {
        this.marks = marks;
    }
    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    
}