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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
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
                .setIssuedAt(new Date(timeStamp)).claim("username", username)
                .claim("email", email).compact();

        Map<String, String> map = new HashMap<>();
        try {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setToken(token);
            authService.registeUser(username, email, password);
            notificationService.sendNotification(newUser);
            map.put("message", "registered successfully");
        } catch (Exception e) {
            map.put("message", e.getMessage());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        try{
            User user = authService.validateUser(email, password);
            return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
        }catch(Exception e){
            Map<String , String > map = new HashMap<>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }     
    }

    @GetMapping("/verifymail/{token}")
    public ResponseEntity<Map<String, String>> verifyMail(@PathVariable String token) {
        Map<String, String> map = new HashMap<>();
        try {
            System.out.println(token);
            // Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token).getBody();
            try {
                Jws<Claims>  jws = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token); 
                if(authService.verifyMail(jws.getBody().get("email").toString())){
                    map.put("message", "email verified successfully");
                }else{
                    map.put("message", "Email verification failed");
                }
            }
            catch (JwtException ex) {  
                System.out.println(ex.getMessage());
            }
            // }
        }catch(Exception e){
             map.put("message", "Email verification failed"); 
        }
            
        return new ResponseEntity<>(map , HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Map<String , String>> forgotPassword( @RequestBody Map<String , Object> userMap){
        Map<String , String> map = new HashMap<>();
        String email = (String) userMap.get("email");

        try{
            long timeStamp = System.currentTimeMillis();
            String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY))
                .claim("email", email)
                .compact();
            authService.ForgotPassword(email , token);
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setToken(token);
            notificationService.sendPasswordResetLink(newUser);
            map.put("message", "Password reset link sent");
        }catch(Exception e){ map.put("message", "Email verification failed"); }
            
        return new ResponseEntity<>(map , HttpStatus.OK);
    }

    @GetMapping("/resetPassword/{token}")
    public ResponseEntity<Map<String , String>> resetPassword( @PathVariable String token){
        Map<String , String> map = new HashMap<>();
        // String password = (String) userMap.get("password");

        try{
            Jws<Claims> jwt = Jwts.parser()                  
                            .setSigningKey(Constants.API_SECRET_KEY)                    
                            .parseClaimsJws(token);
            if(authService.verifyMail(jwt.getBody().get("email").toString())){
                map.put("message", "valid password reset link");
            }else{
                map.put("message", "invalid password reset link");
            }
        }catch(Exception e){ map.put("message", "invalid password reset link"); }
            
        return new ResponseEntity<>(map , HttpStatus.OK);
    }


    private Map<String , String> generateJWTToken(User user){
        long timeStamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
        .setIssuedAt(new Date(timeStamp))
        .setExpiration(new Date(timeStamp + Constants.TOKEN_VALIDITY))
        .claim("userId", user.getUserID())
        .claim("username", user.getUserName())
        .claim("email", user.getEmail())
        .compact();

        Map<String , String > map = new HashMap<>();
        map.put("token", token);
        return map;
    }
    
}