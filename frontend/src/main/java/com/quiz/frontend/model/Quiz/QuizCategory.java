package com.quiz.frontend.model.Quiz;

import java.util.ArrayList;
import java.util.List;
import com.quiz.frontend.model.Quiz.QuizGet;


public class QuizCategory {
    public String category;
    public List<QuizGet> quizList=new ArrayList<QuizGet>();  
    public QuizCategory(String cate){
        category=cate;
    }  
}