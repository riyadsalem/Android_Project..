package com.example.myapplication.model;

public class User {


    String userName = null;
    String password = null;
    String userEmail = null;

    public User() {
    }

    public User(String u, String p, String e) {
        this.userName = u;
        this.password = p;
        this.userEmail = e;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userName = userEmail;
    }
}
