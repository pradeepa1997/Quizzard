package com.chatapp.backend.Services;

import com.chatapp.backend.exceptions.EtAuthException;
import com.chatapp.backend.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(User user) throws MailException{
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("hasanthamalshan07@gmail.com");
        mail.setSubject("registered successfuly");
        mail.setText("proceed here to verify email address" + "<a>http://localhost:8081/auth/verifymail/" + user.getToken() + "</a>") ;
        try{
            javaMailSender.send(mail);
        }catch(Exception e){
            throw new EtAuthException("Email sending failed");
        }
        
    }

    public void sendPasswordResetLink(User user) throws MailException{
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("hasanthamalshan07@gmail.com");
        mail.setSubject("Password reset link");
        mail.setText("proceed here to reset password" + "<a>http://localhost:8081/auth/resetPassword/" + user.getToken() + "</a>") ;

        javaMailSender.send(mail);
    }
}