package com.chatapp.backend.Services;

import java.util.regex.Pattern;

import javax.transaction.Transactional;

import com.chatapp.exceptions.EtAuthException;
import com.chatapp.backend.model.User;
import com.chatapp.backend.repository.AuthRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    public User validateUser(String email, String password) throws EtAuthException {
        if(email != null) email.toLowerCase();
        return authRepository.findByEmailAndPassword(email, password);
    }

    public String registeUser(String username, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null ) email = email.toLowerCase();
        if(!pattern.matcher(email).matches()){
            throw new EtAuthException("Invalid email");
        }
        Integer count = authRepository.getCountByEmail(email);
        if(count > 0){
            throw new EtAuthException("Email already in use");
        }else{
            authRepository.create(username, email, password);
            return "user created";
        }
    }

    public Boolean verifyMail(String email) throws EtAuthException {
        if(authRepository.getCountByEmail(email) == 1){
            try{
                authRepository.verifyEmail(email);
                return true;
            }catch(EtAuthException e){
                return false;
            }  
        }else {
            return false;
        }
    }

    public String ForgotPassword(String email , String token) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null ) email = email.toLowerCase();
        if(!pattern.matcher(email).matches()){
            throw new EtAuthException("Invalid email");
        }
        Integer count = authRepository.getCountByEmail(email);
        if(count > 0){
            return "password reset link sent";   
        }else{
            throw new EtAuthException("No user found");
        }
    }

    public String ResetPassword(String email , String password) throws EtAuthException {
        Integer count = authRepository.resetPassword(email , password);
        if(count > 0){
            return "password reset";   
        }else{
            throw new EtAuthException("error");
        }
    }
}