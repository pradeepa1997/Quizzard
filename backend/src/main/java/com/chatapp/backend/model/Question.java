package com.chatapp.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue
    @Column(name="questionID")
    private Integer questionID;

    @Column(name="quizID") 
    private Integer quizID;

    @Column(name="questionName") 
    private String questionName;

    @Column(name="description") 
    private String description;

    @Column(name="choice1") 
    private String choice1;

    @Column(name="choice2") 
    private String choice2;

    @Column(name="choice3") 
    private String choice3;

    @Column(name="choice4") 
    private String choice4;

    @Column(name="correctAns")
    private String correctAns;
    
    
    
    public String getChoice1() {
        return choice1;
    }
    public String getChoice2() {
        return choice2;
    }
    public String getChoice3() {
        return choice3;
    }
    public String getChoice4() {
        return choice4;
    }
    public String getCorrectAns() {
        return correctAns;
    }
    public String getDescription() {
        return description;
    }
    public Integer getQuestionID() {
        return questionID;
    }
    public String getQuestionName() {
        return questionName;
    }
    public Integer getQuizID() {
        return quizID;
    }
    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }
    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }
    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }
    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }
    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }
    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }
    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }
}