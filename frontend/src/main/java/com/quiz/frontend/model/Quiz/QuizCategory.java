package com.quiz.frontend.model.Quiz;

import java.util.ArrayList;
import java.util.List;



public class QuizCategory {
    public String category;
    public List<QuizGet> quizList=new ArrayList<QuizGet>();  
    List<Quiz> Other = new ArrayList<Quiz>();
    
    
    public QuizCategory(String cate){
        category=cate;
    }  
}