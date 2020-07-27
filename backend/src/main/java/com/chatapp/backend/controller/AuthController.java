package com.chatapp.backend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.chatapp.backend.Constants;
import com.chatapp.backend.Services.AuthService;
import com.chatapp.backend.Services.NotificationService;
import com.chatapp.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    NotificationService notificationService;

    @PostMapping(value = "/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        long timeStamp = System.currentTimeMillis();
                String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                                    .setIssuedAt(new Date(timeStamp))
                                    .setExpiration(new Date(timeStamp))
                                    .claim("username", username)
                                    .claim("email", email)
                                    .compact();

                Map<String , String> map = new HashMap<>();
                try{
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setToken(token);
                    authService.registeUser(username, email, password);
                    notificationService.sendNotification(newUser);
                    map.put("message", "registered successfully");
                }catch(Exception e){
                    map.put("message", e.getMessage());
                }
                return new ResponseEntity<>(map , HttpStatus.OK);
    }
    
}