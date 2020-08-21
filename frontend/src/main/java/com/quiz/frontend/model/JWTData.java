package com.quiz.frontend.model;

import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTData{
    private static String token="";
    private static String userName="";
    private static String email="";
    // private static String userType="";
    private static Integer userId=-1;
    

    public String getToken(){
        return token;
    }
    public void setToken(String t){
        System.out.println(t+"hellllllllll");
        token=t;
        setclaim();
        // System.out.println(getclaim().get("userId"));
    }
    public String getUserName(){
        return userName;
    }

    public Integer getUserId(){
        return userId;
    }

    public String getEmail(){
        return email;
    }
    // public String getuserType(){
    //     return userType;
    // }
    public boolean isLog(){
        if(token.equals("")){
            return false;
        }else{
            return true;
        }
    }
    
    private void setclaim(){
        Claims claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary("quizzardsecretkey"))
            .parseClaimsJws(token).getBody();
        email=claims.get("email").toString();
        userName=claims.get("username").toString();
        userId=Integer.parseInt(claims.get("userId").toString());
        // return claims;
    }
    
}   