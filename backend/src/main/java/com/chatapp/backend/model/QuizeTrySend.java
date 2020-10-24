package com.chatapp.backend.model;

public class QuizeTrySend{
     
    
    private Double marks;

    private String quizName;
    


    // public QuizeTrySend(Integer quizeName,Double mark){
    //     this.quizName=quizName;
    //     this.marks=mark;
    // }

    public String getQuizName() {
        return quizName;
    }
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    
    public Double getMarks() {
        return marks;
    }
    
    public void setMarks(Double marks) {
        this.marks = marks;
    }
    
}





