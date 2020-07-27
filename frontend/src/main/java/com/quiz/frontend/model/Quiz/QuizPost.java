package com.quiz.frontend.model.Quiz;


public class QuizPost{

    private String quizeCategory;
    private String other;
    private String quizName;
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    public void setQuizeCategory(String quizeCategory) {
        this.quizeCategory = quizeCategory;
    }
    public String getQuizName() {
        return quizName;
    }
    public String getQuizeCategory() {
        return quizeCategory;
    }
    public String getOther() {
        return other;
    }
    public void setOther(String other) {
        this.other = other;
    }
}