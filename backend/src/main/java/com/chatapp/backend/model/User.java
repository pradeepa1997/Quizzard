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



    @Column(name="token")
    private String token;

    @Column(name="is_verified")
    private Boolean is_verified;

    public User(int userID, String username, String email, String password, String userType, String token,
			boolean is_verified) {
                this.userID = userID;
                this.userName = username;
                this.email = email;
                this.password = password;
                this.userType = userType;
                this.token = token;
                this.is_verified = is_verified;
	
	public User() {
	}
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
    public String getUserType() {
        return userType;
    }
    public String getToken() {
        return token;
    }
    public Boolean getIsVerified() {
        return is_verified;
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
    public void setToken(String token){
        this.token = token;
    }
    public void setIsVerified(Boolean is_verified){
        this.is_verified = is_verified;
    }

    // public Users(String name,String email,String password){
        
    //     this.userName=name;
    //     this.email=email;
    //     this.password=password;
    // }
 
    
}