package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void clk(View view){
//        EditText u = findViewById(R.id.num_store);


        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        User user = new User("gus@gmail.com", "pass1234");
        Call<User> call = retrofitAPI.createPost(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // this method is called when we get response from our api.
//                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // we are getting response from our body
                // and passing it to our modal class.
                User responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code()
                        + "\nAccess token : " + responseFromAPI.getAccess_token()
                        + "\n" + "Token Type : " + responseFromAPI.getToken_type()
                        + "\n" + "User : " + responseFromAPI.getUser();

                // below line we are setting our
                // string to our text view.
//                responseTV.setText(responseString);
                Log.i("vac", "accept: " + responseString);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
//                responseTV.setText("Error found is : " + t.getMessage());
                Log.i("vac", "Error found is : " + t.getMessage());
            }
        });

//        retrofit.create(GitHubService.class)
//                .userLogin(new User("gus@gmail.com", "pass1234"));

//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<User>() {
//                    @Override
//                    public void accept(User user) throws Exception {
////                        Log.i("vac1", "accept: " + user.access_token);
////                        Log.i("vac1", "accept: " + user.token_type);
//                    }
//                });

        retrofit.create(GitHubService.class)
                .getId("base0")
//                .getUser(u.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
                .subscribe(new Consumer<User>() {
                    @Override
//                    public void accept(String s) throws Exception {
                    public void accept(User user) throws Exception {


//                        Log.i("vac1", "accept: " + user);
//                        Log.i("vac1", "accept: " + user.getName());
//                        Log.i("vac1", "accept: " + user.getCompany());

//                        Log.i("vac1", "accept: " + s);
//                        JSONArray jsons = new JSONArray(s);
//                        Log.i("vac", "accept: " + jsons.get(1));
//                        for (int i = 0; i < jsons.length();i++) {
//                            JSONObject json = (JSONObject) jsons.get(i);
//                            Log.i("vac", "accept: " + json.get("name"));
//                        }
                    }
                });

//        Log.i("vac", "accept: " + repos.toString());
//        textView.setText(repos.toString());
    }
}
