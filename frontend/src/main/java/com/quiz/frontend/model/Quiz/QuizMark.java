package com.quiz.frontend.model.Quiz;

public class QuizMark {
    Integer userID;
    Integer quizID;
    Double marks;
    public void setMarks(Double marks) {
        this.marks = marks;
    }
    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public Double getMarks() {
        return marks;
    }
    public Integer getQuizID() {
        return quizID;
    }
    public Integer getUserID() {
        return userID;
    }
}