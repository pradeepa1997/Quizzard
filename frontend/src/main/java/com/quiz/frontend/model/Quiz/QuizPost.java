package com.quiz.frontend.model.Quiz;


public class QuizPost{

    private String quizeCategory;
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
}