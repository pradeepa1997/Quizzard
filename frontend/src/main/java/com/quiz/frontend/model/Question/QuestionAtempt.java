package com.quiz.frontend.model.Question;

import java.util.ArrayList;
import java.util.List;

import com.quiz.frontend.model.Quiz.QuizGet;

public class QuestionAtempt {
    private QuizGet quiz;
    private List<Question> questions=new ArrayList<Question>();

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public void setQuiz(QuizGet quiz) {
        this.quiz = quiz;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public QuizGet getQuiz() {
        return quiz;
    }
}