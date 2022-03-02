package com.example.mobile_daily_money.API;

import com.example.mobile_daily_money.User.Model.Request.LoginRequest;
import com.example.mobile_daily_money.User.Model.Request.RegisterRequest;
import com.example.mobile_daily_money.User.Model.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("user/register")
    Call<APIMessage> register(@Body RegisterRequest registerRequest);

}
