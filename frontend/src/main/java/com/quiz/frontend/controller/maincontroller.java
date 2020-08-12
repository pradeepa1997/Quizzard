package com.quiz.frontend.controller;



//  com.quiz.frontend.model.;
import com.quiz.frontend.model.JWTData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class maincontroller {

    JWTData jwttoken=new JWTData();

    @RequestMapping(value = "/")
    public String index(final Model model){
        return "index";
    }

    

    @RequestMapping(value = "/authenticate")
    public String authenticate() {
        System.out.println(jwttoken.getToken()+"hiiiii");
        if(jwttoken.getToken()==""){
            return ("redirect:login");
        }else{
            return ("redirect:home");
        }
    }

    @RequestMapping(value = "/quiz")
    public String quiz(final Model model) {
        return "quiz";
    }

    // @RequestMapping(value = "/admin/users")
    // public String admin(final Model model) {
    //     return "adminpanel";
    // }
}