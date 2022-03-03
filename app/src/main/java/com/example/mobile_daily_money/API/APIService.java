package com.example.mobile_daily_money.API;

import com.example.mobile_daily_money.Authentication.TokenRequest;
import com.example.mobile_daily_money.Authentication.TokenResponse;
import com.example.mobile_daily_money.MoneyRecord.Model.Request.MoneyRecordRequest;
import com.example.mobile_daily_money.MoneyRecord.Model.Response.MoneyRecords;
import com.example.mobile_daily_money.User.Model.Request.LoginRequest;
import com.example.mobile_daily_money.User.Model.Request.RegisterRequest;
import com.example.mobile_daily_money.User.Model.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {

    //USER API
    @POST("user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("user/register")
    Call<APIMessage> register(@Body RegisterRequest registerRequest);

    @POST("user/token")
    Call<TokenResponse> getToken(@Body TokenRequest tokenRequest);


    //MONEY RECORD API
    @GET("money-record")
    Call<MoneyRecords> getMoneyRecords(@Header("Authorization") String auth);

    @POST("money-record")
    Call<APIMessage> createMoneyRecord(@Header("Authorization") String auth, @Body MoneyRecordRequest moneyRecordRequest);
}
