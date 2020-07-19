package com.chatapp.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    @Column(name="userID") 
    private Integer userID;
    @Column(name="userName")
    private String userName;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Integer getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    // public Users(String name,String email,String password){
        
    //     this.userName=name;
    //     this.email=email;
    //     this.password=password;
    // }
 
    
}