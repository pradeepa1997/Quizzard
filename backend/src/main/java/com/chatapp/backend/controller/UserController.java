package com.chatapp.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.chatapp.backend.Constants;
import com.chatapp.backend.Services.AuthService;
import com.chatapp.backend.model.User;
import com.chatapp.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserRepository usersrepo;

    @Autowired
    AuthService authService;

    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return usersrepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getByID(@PathVariable final Integer id) {
        return usersrepo.findById(id);
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