package com.example.retrofit;

import com.google.gson.annotations.Expose;

public class User {
//    @Expose
//    String name;
//
//    @Expose
//    String company;
//
//    public String getName() {
//        return name;
//    }
//
//    public String getCompany() {
//        return company;
//    }

    private String email;
    private String password;
    @Expose
    String access_token;
    @Expose
    String token_type;
    @Expose
    Auth user;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getUser() {
        return user.getId()+","+user.getName();
    }
}
