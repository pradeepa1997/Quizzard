package com.quiz.frontend.model.Question;

public class QuestionGet {
    private String description;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String correctAns;
    private Integer quizID;
    private Integer questionID;
    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }
    public Integer getQuestionID() {
        return questionID;
    }
    public Integer getQuizID() {
        return quizID;
    }
    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }
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
}

