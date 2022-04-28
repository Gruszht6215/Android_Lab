package com.example.retrofit;

import com.google.gson.annotations.Expose;

public class Auth {
    @Expose
    String id;
    @Expose
    String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
