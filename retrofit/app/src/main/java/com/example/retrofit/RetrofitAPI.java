package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("/api/auth/login")
    Call<User> createPost(@Body User user);
}
