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

    @Column(name="userType")
    private String userType;


    public String getEmail(){
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
    public String getUserType() {
        return userType;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    public void setUserID(final Integer userID) {
        this.userID = userID;
    }
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    // public Users(String name,String email,String password){
        
    //     this.userName=name;
    //     this.email=email;
    //     this.password=password;
    // }
 
    
}