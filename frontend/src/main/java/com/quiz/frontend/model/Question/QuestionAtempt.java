package com.quiz.frontend.model.Question;

import java.util.ArrayList;
import java.util.List;

import com.quiz.frontend.model.Quiz.QuizGet;

public class QuestionAtempt {
    private QuizGet quiz;
    private List<QuestionGet> questions=new ArrayList<QuestionGet>();

    public void setQuestions(List<QuestionGet> questions) {
        this.questions = questions;
    }
    public void setQuiz(QuizGet quiz) {
        this.quiz = quiz;
    }
    public List<QuestionGet> getQuestions() {
        return questions;
    }
    public QuizGet getQuiz() {
        return quiz;
    }
}