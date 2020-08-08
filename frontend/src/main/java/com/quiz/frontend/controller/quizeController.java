package com.quiz.frontend.controller;

import com.quiz.frontend.model.Question.Question;
import com.quiz.frontend.model.Quiz.Quiz;
import com.quiz.frontend.model.Quiz.QuizPost;
import com.quiz.frontend.model.Quiz.QuizeGet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class quizeController {
    
    @GetMapping(value = "/addquiz")
    public String addQuiz(final Model model) {
        // getEmployees();
        model.addAttribute("quiz", new QuizPost());
        model.addAttribute("error", false);
        return "addquiz"; 
    }

    
    @PostMapping("/addquiz")
    public String addQuizSubmit(@ModelAttribute QuizPost quizPost,final Model model){
        
        final String url = "http://localhost:8081/api/quiz/add";
        Quiz quiz;
        if(quizPost.getOther()!=null && quizPost.getOther().equals("")){
            quiz=new Quiz(1,quizPost.getQuizeCategory(),quizPost.getQuizName());
        }else{
            quiz=new Quiz(1,quizPost.getOther(),quizPost.getQuizName());
        }
        RestTemplate restTemplate = new RestTemplate();
        String quizid=restTemplate.postForObject(url,quiz ,String.class);
        System.out.println(quizid);
         
        try{
            Question question= new Question();
            question.setQuizID(Integer.parseInt(quizid));
            System.out.println(question.getQuizID()+10);
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


    private static void getEmployees(){
        // getForObject(url, Quiz[].class)
        final String uri = "http://localhost:8081/api/quiz/all";
        RestTemplate restTemplate = new RestTemplate();
        QuizeGet[] result = restTemplate.getForObject(uri, QuizeGet[].class);
        // System.out.println(result[0].getQuizName());
    }
   
}