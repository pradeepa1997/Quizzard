package com.quiz.frontend.model.Quiz;


public class QuizGet {

    private Integer quizID;
    private String quizCategory;
    private String quizName;
    private Integer creatorID;
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    public void setQuizCategory(String quizCategory) {
        this.quizCategory = quizCategory;
    }
    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }
    public String getQuizName() {
        return quizName;
    }
    public void setCreatorID(Integer creatorID) {
        this.creatorID = creatorID;
    }
    public String getQuizCategory() {
        return quizCategory;
    }
    public Integer getCreatorID() {
        return creatorID;
    }

    public Integer getQuizID() {
        return quizID;
    }
    

}