package com.quiz.frontend.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.xml.bind.DatatypeConverter;

public class JWTData{
    static String token="";
    private static userName="";
    private static email="";
    private static userId="";
    

    public String getToken(){
        return token;
    }
    public void setToken(String t){
        System.out.println(t+"hellllllllll");
        token=t;
        setclaim();
        // System.out.println(getclaim().get("userId"));
    }
    String getUserName(){
        return userName;
    }

    String getUserId(){
        return userId;
    }

    String getEmail(){
        return email;
    }
    
    private setclaim(){
        Claims claims = Jwts.parser()
        .setSigningKey(DatatypeConverter.parseBase64Binary("quizzardsecretkey"))
        .parseClaimsJws(token).getBody();
        email=claims.get("email");
        userName=claims.get("username");
        userId=claims.get("userId");
        // return claims;
    }
    
}   