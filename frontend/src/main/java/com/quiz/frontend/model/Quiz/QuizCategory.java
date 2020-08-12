package com.quiz.frontend.model.Quiz;

import java.util.ArrayList;
import java.util.List;
import com.quiz.frontend.model.Quiz.Quiz;


public class QuizCategory {
    public String category;
    List<Quiz> Other = new ArrayList<Quiz>();
    public List<Quiz> quizList=new ArrayList<Quiz>();  
    public QuizCategory(String cate){
        category=cate;
    }  
}