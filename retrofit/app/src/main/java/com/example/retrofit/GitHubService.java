package com.example.retrofit;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface GitHubService {
    @GET("/users/{username}")
    Observable<User> getId(@Path("username") String username);

    @GET("/users/{username}/repos")
    Observable<String> getUser(@Path("username") String username);

    @GET("/users/{username}")
    Observable<String> getUser1(@Path("username") String username);

    @POST("/api/auth/login")
//    String userLogin(@Body User body);
    Observable<User> userLogin(@Body User body);
}
