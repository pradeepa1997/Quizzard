package com.quiz.frontend.controller;

import com.quiz.frontend.model.Question.Question;
import com.quiz.frontend.model.Question.QuestionAtempt;
import com.quiz.frontend.model.Quiz.Quiz;
import com.quiz.frontend.model.Quiz.QuizPost;

import java.util.ArrayList;

import com.quiz.frontend.model.JWTData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class quizeController {
    
    JWTData jwttoken=new JWTData();
    static public QuestionAtempt questionAtempt;

    @GetMapping(value = "/addquiz")
    public String addQuiz(final Model model) {
        if(!jwttoken.isLog()){
            return ("redirect:login");
        }
        model.addAttribute("quiz", new QuizPost());
        model.addAttribute("error", false);
        return "addquiz"; 
    }
    
    @PostMapping("/addquiz")
    public String addQuizSubmit(@ModelAttribute QuizPost quizPost,final Model model){
        
        final String url = "http://localhost:8081/api/quiz/add";
        
        Quiz quiz;
        if(quizPost.getOther()==null || quizPost.getOther().equals("")){
            quiz=new Quiz(jwttoken.getUserId(),quizPost.getQuizeCategory(),quizPost.getQuizName());
        }else{
            quiz=new Quiz(jwttoken.getUserId(),quizPost.getOther(),quizPost.getQuizName());
        }
        
        RestTemplate restTemplate = new RestTemplate();
        String quizid=restTemplate.postForObject(url,quiz ,String.class);
        
         
        try{
            Question question= new Question();
            question.setQuizID(Integer.parseInt(quizid));
            System.out.println(question.getQuizID());
            model.addAttribute("questions",question);
            model.addAttribute("alert",false);
            return "question";   
        }catch( Exception e){
            QuizPost data=new QuizPost();
            data.setQuizName(quizPost.getQuizName());
            data.setOther(quizPost.getOther());
            data.setQuizeCategory(quizPost.getQuizeCategory());
            model.addAttribute("quiz",data);
            model.addAttribute("error", true);
            return "addquiz";
        }
        
    }
    @PostMapping("/addquestion")
    public String addQuestionSubmit(@ModelAttribute Question question,final Model model){

        final String url = "http://localhost:8081/api/question/add";
        System.out.println(question.getQuizID());
        RestTemplate restTemplate = new RestTemplate();
        String quizid=restTemplate.postForObject(url,question,String.class);
        System.out.println(quizid);
        model.addAttribute("alert",true);
        Question temp= new Question();
        temp.setQuizID(Integer.parseInt(quizid));
        model.addAttribute("questions",temp);
        return "question";   
        
    }
    @GetMapping("/attemptquiz/{quizid}/{question}")
    public String AttemptQuiz(@PathVariable final Integer quizid,@PathVariable final Integer question,final Model model){
        
        Boolean[] boolArray = new Boolean[questionAtempt.getQuestions().size()-1];
        for (int i=0; i<questionAtempt.getQuestions().size()-1;i++ ){
            if(i<question){
                boolArray[i]=true;
            }else{
                boolArray[i]=false;
            }
        }

        model.addAttribute("quiz",questionAtempt.getQuiz());
        model.addAttribute("question",questionAtempt.getQuestions().get(question-1));
        model.addAttribute("boolArray",boolArray);
        model.addAttribute("username",jwttoken.getUserName());
        model.addAttribute("userID",jwttoken.getUserId());
        return "attemptquiz";   
        
    }
    
    @GetMapping("/attemptquiz/{id}")
    public String AttemptQuiz(@PathVariable final Integer id,final Model model){

        final String url = "http://localhost:8081/api/quiz/"+id;
        System.out.println(id);
        RestTemplate restTemplate = new RestTemplate();
        QuestionAtempt result=restTemplate.getForObject(url, QuestionAtempt.class);
        System.out.println(result.getQuestions().size());
        questionAtempt=result;
        
        return ("redirect:/attemptquiz/"+id+"/"+1);
          
        
    }

    


   
   
}