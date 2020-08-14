package com.chatapp.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import com.chatapp.backend.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chatapp.backend.Services.AuthService;

import com.chatapp.backend.repository.Quizrepo;
import com.chatapp.backend.repository.Quiztryrepo;
import com.chatapp.backend.repository.UserRepository;

import com.chatapp.backend.model.User;
import com.chatapp.backend.model.Quiz;
import com.chatapp.backend.model.Quiztry;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserRepository usersrepo;

    @Autowired
    Quizrepo quizrepo;

    @Autowired
    Quiztryrepo quiztryrepo;

    @Autowired
    AuthService authService;

    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return usersrepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public ArrayList<Object> getByID(@PathVariable final Integer id) {
        Optional<User> user = usersrepo.findById(id);
        List<Quiz> quiz = quizrepo.findBycreatorID(id);
        List<Quiztry> quiztry = quiztryrepo.findByuserID(id);
        int quizSize = quiz.size();
        int quiztrySize = quiztry.size();
        ArrayList<Object> list =new ArrayList<Object>();
        list.add(user);
        list.add(quiz);
        list.add(quizSize);
        list.add(quiztry);
        list.add(quiztrySize);
        return list;
    }

    @PostMapping(value = "/add")
    public String addNewUser(@RequestBody final User user) {
        System.out.println(user.getEmail());
        usersrepo.save(user);
        return "User added"; 
    }

    @PutMapping(value = "/update")
    public String updateUser(@RequestBody final User user) {
        String Upstatus = null ;
        System.out.println(user.getUserName());
        if(user.getUserID() != null){
            usersrepo.save(user);
            Upstatus = "update successfull";
        }
        else{Upstatus = "ERROR : update not successfull";}
        System.out.print(user);
        return Upstatus; 
    }

    @GetMapping("/getUser/{token}")
    public User verifyMail(@PathVariable String token) {
        Map<String, String> map = new HashMap<>();
        try {
            try {
                Jws<Claims>  jws = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token); 
                User user = authService.getUser(jws.getBody().get("email").toString());
                return user;
            }
            catch (JwtException ex) {  
                return new User();
            }
        }catch(Exception e){
             return new User();
        }
    }
    
}