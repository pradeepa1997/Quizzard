package com.quiz.frontend.controller;

import java.net.MalformedURLException;

import com.quiz.frontend.model.User.ForgotUser;
import com.quiz.frontend.model.User.LoginUser;
import com.quiz.frontend.model.User.RegisterUser;
import com.quiz.frontend.model.User.ResetUser;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import com.quiz.frontend.model.JWTData;

@Controller
public class authController {



    JWTData jwttoken=new JWTData();

    @GetMapping(value = "/login")
    public String login(final Model model) throws MalformedURLException {
        model.addAttribute("user", new LoginUser());
        return "auth/login";
    }
    
    @GetMapping(value = "/register")
    public String register(final Model model) {
        model.addAttribute("user", new RegisterUser());
        return "auth/register";
    }

    @GetMapping(value = "/forgotPassword")
    public String forgotPassword(final Model model) {
        model.addAttribute("user", new ForgotUser());
        return "auth/forgotPassword";
    }

    
    @GetMapping(value = "/verifymail/{token}")
    public String verifyMail(final Model model , @PathVariable String token) throws MalformedURLException {
        final String url = "http://localhost:8081/auth/verifymail/" + token;
        RestTemplate restTemplate = new RestTemplate();
        try{
            String resBody = restTemplate.getForObject(url,String.class);
            JSONObject res = new JSONObject(resBody);
            System.out.println(res.get("message"));
            if(res.get("message").equals("email verified successfully")){
                model.addAttribute("user", new LoginUser());
                model.addAttribute("success", true);
                model.addAttribute("successmessage", "Email verified successfully !");
                return  "auth/login"; 
            }else{
                model.addAttribute("user", new LoginUser());
                model.addAttribute("error", true);
                model.addAttribute("errormessage", res.get("message"));
                return  "auth/login"; 
            }
            
        }catch(Exception e){
            model.addAttribute("user", new LoginUser());
            model.addAttribute("error", true);
            model.addAttribute("errormessage", "something went wrong");
            return  "auth/login"; 
        }
    }

    @GetMapping(value = "/resetPassword/{token}")
    public String passwordReset(final Model model , @ModelAttribute LoginUser user , @PathVariable String token) {
        final String url = "http://localhost:8081/auth//verifyPasswordResetToken/" + token;
        RestTemplate restTemplate = new RestTemplate();
        try{
            String resBody = restTemplate.getForObject(url,String.class);
            JSONObject res = new JSONObject(resBody);
            try{
                System.out.println(res.get("message"));
                if(res.get("message").equals("invalid password reset link")){
                    model.addAttribute("user", new LoginUser());
                    model.addAttribute("error", true);
                    model.addAttribute("errormessage", res.get("message"));
                    return "auth/login";
                }else{
                    model.addAttribute("user", new ResetUser());
                    model.addAttribute("token", token);
                    return "auth/resetPassword";
                }
            }catch(Exception e){
                System.out.println(res.get("message"));
                model.addAttribute("user", new LoginUser());
                model.addAttribute("error", true);
                model.addAttribute("errormessage", res.get("message"));
                return "auth/login";
            }    
        }catch(Exception e){
            model.addAttribute("user", new LoginUser());
            model.addAttribute("error", true);
            model.addAttribute("errormessage", "something went wrong");
            return "auth/login";
        }
        
    }

    @PostMapping("/setNewPassword/{token}")
    public String setNewPassword(final Model model , @ModelAttribute ResetUser user ,@PathVariable String token) {
        final String url = "http://localhost:8081/auth/resetPassword/" + token;
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(user.getPassword());
        try{
            String resBody = restTemplate.postForObject(url, user, String.class);
            JSONObject res = new JSONObject(resBody);
            try{
                System.out.println(res.get("message"));
                model.addAttribute("user", new LoginUser());
                model.addAttribute("success", true);
                model.addAttribute("successmessage", "Password reset successfully !");
                return "auth/login";
            }catch(Exception e){
                System.out.println(res.get("message"));
                model.addAttribute("user", new LoginUser());
                model.addAttribute("error", true);
                model.addAttribute("errormessage", res.get("message"));
                return "auth/login";
            }    
        }catch(Exception e){
            model.addAttribute("user", new LoginUser());
            model.addAttribute("error", true);
            model.addAttribute("errormessage", "something went wrong");
            return "auth/login";
        }
        
    }

    @PostMapping(value = "/register")
    public String registerUser(final Model model , @ModelAttribute RegisterUser user) {
        final String url = "http://localhost:8081/auth/register/";
        RestTemplate restTemplate = new RestTemplate();
        try{
            String resBody = restTemplate.postForObject(url, user, String.class);
            JSONObject res = new JSONObject(resBody);
            System.out.println(res.getString("message"));
            try{
                if(res.getString("message").equals("invalid details")){
                    // RegisterUser temp=new RegisterUser();
                    model.addAttribute("user",user);
                    model.addAttribute("error",true);
                    return "auth/register";
                }else if(res.getString("message").equals("Email already in use")){
                    model.addAttribute("user",user);
                    model.addAttribute("error",true);
                    return "auth/register";
                }
                
                model.addAttribute("user", new LoginUser());
                model.addAttribute("success", true);
                model.addAttribute("successmessage", "registered successfully ! your account verification link has been sent to your email");
                return "auth/login";
            }catch(Exception e){
                System.out.println(res.get("message"));
                model.addAttribute("user", new RegisterUser());
                model.addAttribute("error", true);
                model.addAttribute("errormessage", res.get("message"));
                return "auth/register";
            }    
        }catch(Exception e){
            model.addAttribute("user", new RegisterUser());
            model.addAttribute("error", true);
            model.addAttribute("errormessage", "something went wrong");
            return "auth/register";
        }
    }

    @PostMapping(value = "/login")
    public String loginUser(final Model model , @ModelAttribute LoginUser user) {
        final String url = "http://localhost:8081/auth/login/";
        RestTemplate restTemplate = new RestTemplate();
        try{
            String resBody = restTemplate.postForObject(url, user, String.class);
            JSONObject res = new JSONObject(resBody);
            try{

                jwttoken.setToken(res.get("token")+"");
                System.out.println(jwttoken.getUserName());
                return ("redirect:home");

            }catch(Exception e){

                System.out.println(res.get("message"));
                model.addAttribute("user", new LoginUser());
                model.addAttribute("error", true);
                model.addAttribute("errormessage", res.get("message"));
                return "auth/login";
                
            }    
        }catch(Exception e){
            model.addAttribute("user", new LoginUser());
            model.addAttribute("error", true);
            model.addAttribute("errormessage", "something went wrong");
            return "auth/login";
        }
        
    }

    @PostMapping(value = "/forgotPassword")
    public String forgot(final Model model , @ModelAttribute ForgotUser user) {
        final String url = "http://localhost:8081/auth/forgotPassword";
        RestTemplate restTemplate = new RestTemplate();
        try{
            String resBody = restTemplate.postForObject(url, user, String.class);
            JSONObject res = new JSONObject(resBody);

            System.out.println(res.get("message"));

            if(res.get("message").equals("Password reset link sent")){
                model.addAttribute("user", new LoginUser());
                model.addAttribute("success", true);
                model.addAttribute("successmessage", "Password reset link has been sent");
                return "auth/login";
            }else{
                model.addAttribute("user", new LoginUser());
                model.addAttribute("error", true);
                model.addAttribute("errormessage", "Email verification failed");
                return "auth/login";
            }
        }catch(Exception e){
            model.addAttribute("user", new LoginUser());
            model.addAttribute("error", true);
            model.addAttribute("errormessage", "something went wrong");
            return "auth/login";
        }
        
    }
}