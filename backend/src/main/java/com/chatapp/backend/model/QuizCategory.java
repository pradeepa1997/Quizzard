package com.chatapp.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class QuizCategory {
    @Id
    @GeneratedValue
    @Column(name="quizID") 
    private Integer categoryID;

    @Column(name="quizCategory")
    private String category;
}