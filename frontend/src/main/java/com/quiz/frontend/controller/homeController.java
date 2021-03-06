package com.quiz.frontend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import com.quiz.frontend.model.Quiz.Quiz;
import com.quiz.frontend.model.Quiz.QuizGet;
import com.quiz.frontend.model.Quiz.QuizCategory;
import com.quiz.frontend.model.JWTData;


@Controller
public class homeController {

    JWTData jwttoken=new JWTData();

    @GetMapping("/home")
    public String home(final Model model){
        final String url = "http://localhost:8081/api/quiz/all";
        final RestTemplate restTemplate = new RestTemplate();

        if(!jwttoken.isLog()){
            return ("redirect:login");
        }

        final QuizGet[] quizes = restTemplate.getForObject(url, QuizGet[].class);
        
        List<QuizCategory> quizesWithCategory = new ArrayList<QuizCategory>();
       

        QuizCategory Maths = new QuizCategory("Maths");
        QuizCategory Science = new QuizCategory("Science");
        QuizCategory Arts = new QuizCategory("Arts");
        QuizCategory IT = new QuizCategory("IT");
        QuizCategory Other = new QuizCategory("Other");
        
      

        for (final QuizGet quiz : quizes) {
            
            System.out.println(quiz.getQuizID());
            System.out.println(quiz.getCreatorID());
            if(quiz.getQuizCategory().equals("Maths")){    
                Maths.quizList.add(quiz);
            }else if(quiz.getQuizCategory().equals("Science")){    
                 Science.quizList.add(quiz);
            }else if(quiz.getQuizCategory().equals("Art")){    
                Arts.quizList.add(quiz);
            }else if(quiz.getQuizCategory().equals("IT")){    
                IT.quizList.add(quiz);
            }else{    
                Other.quizList.add(quiz);
            }
        }
        quizesWithCategory.add(Maths);
        quizesWithCategory.add(Science);
        quizesWithCategory.add(Arts);
        quizesWithCategory.add(IT);
        quizesWithCategory.add(Other);
        model.addAttribute("quizes", quizesWithCategory);
        model.addAttribute("username",jwttoken.getUserName());
        model.addAttribute("userID",jwttoken.getUserId());
        model.addAttribute("searchList",quizes);
        return "home";
    }  
}
