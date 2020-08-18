package com.quiz.frontend.controller;

import com.quiz.frontend.model.Question.Question;
import com.quiz.frontend.model.Question.QuestionAtempt;
import com.quiz.frontend.model.Quiz.Quiz;
import com.quiz.frontend.model.Quiz.QuizAnswer;
import com.quiz.frontend.model.Quiz.QuizPost;

import java.util.ArrayList;
import java.util.List;

import com.quiz.frontend.model.JWTData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class quizeController {
    
    JWTData jwttoken=new JWTData();
    static public QuestionAtempt questionAtempt;
    static public List<Integer> answers=new ArrayList<Integer>();
    // static public List<String> 

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

        Boolean[] boolArray = new Boolean[questionAtempt.getQuestions().size()];
        for (int i=0; i<questionAtempt.getQuestions().size();i++ ){
            System.out.println(i);
            if(i<question-1){
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
        model.addAttribute("nextQuestion",question+1);
        model.addAttribute("preQuestion",question-1);
        model.addAttribute("answer",new QuizAnswer());
        
        if(question==questionAtempt.getQuestions().size()){
            model.addAttribute("next",false);
            model.addAttribute("previous",true);
            model.addAttribute("finish",true);
        }else if(question==1){
            model.addAttribute("next",true);
            model.addAttribute("previous",false);
            model.addAttribute("finish",false);
        }else{
            model.addAttribute("next",true);
            model.addAttribute("previous",true);
            model.addAttribute("finish",false);
        }
        
        return "attemptquiz";   
        
    }

    @PostMapping("/attemptquiz/{quizid}/{question}")
    public String saveAnswer(@PathVariable final Integer quizid,@PathVariable final Integer question,@ModelAttribute QuizAnswer answer){
        
        System.out.println(answer.getAnswer());
        answers.set(question-2,Integer.parseInt(answer.getAnswer()));
        if(question-1==questionAtempt.getQuestions().size()){
            return ("redirect:/finishquiz");   
        }else{
            return ("redirect:/attemptquiz/"+quizid+"/"+(question));   
        }
    }
    
    @RequestMapping("/finishquiz")
    public String finishQuiz(){
        System.out.println("\n\n\n\n\n\n\n\n");
        // System.out.println(answer.getAnswer());
        // answers.set(questionAtempt.getQuestions().size()-1,Integer.parseInt(answer.getAnswer()));
        for (int i=0;i<questionAtempt.getQuestions().size();i++){
            System.out.println(answers.get(i)+"000000000000000000000000");
        }
        return ("redirect:/finalmark");   
    }

    @RequestMapping("/finalmark")
    public String finalMark(final Model model){
        System.out.println("\n\n\n\n\n\n\n\n");
        // System.out.println(answer.getAnswer());
        // answers.set(questionAtempt.getQuestions().size()-1,Integer.parseInt(answer.getAnswer()));
        int count=0;
        for (int i=0;i<questionAtempt.getQuestions().size();i++){
            System.out.println(answers.get(i)+"  ---------------  " + questionAtempt.getQuestions().get(i).getCorrectAns());
            if(Integer.parseInt(questionAtempt.getQuestions().get(i).getCorrectAns())==answers.get(i)){
                count++;
            }
        }
        model.addAttribute("count",count);
        return "finalMarks";   
    }


    @GetMapping("/attemptquiz/{id}")
    public String AttemptQuiz(@PathVariable final Integer id,final Model model){

        final String url = "http://localhost:8081/api/quiz/"+id;
        System.out.println(id);
        RestTemplate restTemplate = new RestTemplate();
        QuestionAtempt result=restTemplate.getForObject(url, QuestionAtempt.class);
        System.out.println(result.getQuestions().size());
        for(int i=0;i<result.getQuestions().size();i++){
            answers.add(0);
        }
        questionAtempt=result;
        return ("redirect:/attemptquiz/"+id+"/"+1);
    }
    

    


   
   
}