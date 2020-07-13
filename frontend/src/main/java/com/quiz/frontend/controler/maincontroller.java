package com.quiz.frontend.controler;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class maincontroller {
    @RequestMapping(value = "/index")
   public String index(Model model){
       String name="pradeepa Sandaruwan";
    //    data ("pradeepa");
    //    data.add("Sandaruwan");
        model.addAttribute("name",name);  
        return "index";
   }
}