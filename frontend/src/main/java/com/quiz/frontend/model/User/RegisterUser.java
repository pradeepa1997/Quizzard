package com.quiz.frontend.model.User;


public class RegisterUser{
    private String userID;
    private String userName ;
    private String email ;
    private String password ;
    private String reTypePassword ;

    public void User() {
        
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getreTypePassword() {
        return reTypePassword;
    }

    public void setreTypePassword(final String reTypePassword) {
        this.reTypePassword = reTypePassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}