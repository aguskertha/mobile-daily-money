package com.example.mobile_daily_money.User.Repository;

import android.content.Context;
import android.widget.Toast;

import com.example.mobile_daily_money.API.APIConfig;
import com.example.mobile_daily_money.API.APIMessage;
import com.example.mobile_daily_money.Authentication.TokenManager;
import com.example.mobile_daily_money.Authentication.TokenResponse;
import com.example.mobile_daily_money.User.Listener.LoginListener;
import com.example.mobile_daily_money.User.Listener.LogoutListener;
import com.example.mobile_daily_money.User.Listener.RegisterListener;
import com.example.mobile_daily_money.User.Model.Request.LoginRequest;
import com.example.mobile_daily_money.User.Model.Request.RegisterRequest;
import com.example.mobile_daily_money.User.Model.Response.LoginResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    public static final void login(LoginRequest loginRequest, Context context, LoginListener loginListener){
        try {
            TokenManager tokenManager = TokenManager.getInstance(context.getSharedPreferences("Token",0));
            Call<LoginResponse> loginResponseCall = APIConfig.getService(tokenManager).login(loginRequest);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    try {
                        if(response.isSuccessful()){
                            if(response.body()==null){
                                throw new Exception("Response Body is Null");
                            }
                            tokenManager.saveToken(response.body());
                            loginListener.onLogin(response.body());
                        }
                        else if(response.code() == 400){
                            APIMessage message = new Gson().fromJson(response.errorBody().charStream(), APIMessage.class);
                            Toast.makeText(context, message.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Something Wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception err){
                        Toast.makeText(context, err.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });
        }
        catch (Exception err){
            Toast.makeText(context, err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static final void register(RegisterRequest registerRequest, Context context, RegisterListener registerListener){
        try{
            TokenManager tokenManager = TokenManager.getInstance(context.getSharedPreferences("Token",0));
            Call<APIMessage> registerResponseCall = APIConfig.getService(tokenManager).register(registerRequest);
            registerResponseCall.enqueue(new Callback<APIMessage>() {
                @Override
                public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                    if(response.isSuccessful()){
                        APIMessage message = response.body();
                        registerListener.onRegister(message);
                    }
                    else if(response.code() == 400){
                        APIMessage message = new Gson().fromJson(response.errorBody().charStream(), APIMessage.class);
                        Toast.makeText(context, message.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "Something Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<APIMessage> call, Throwable t) {

                }
            });
        }
        catch (Exception err){
            Toast.makeText(context, err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static final void logout(Context context, TokenResponse tokenResponse, LogoutListener logoutListener){
        try {
            TokenManager tokenManager = TokenManager.getInstance(context.getSharedPreferences("Token",0));
            Call<APIMessage> callLogout = APIConfig.getService(tokenManager).logout("Bearer "+tokenResponse.getToken());
            callLogout.enqueue(new Callback<APIMessage>() {
                @Override
                public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                    if(response.isSuccessful()){
                        APIMessage message = response.body();
                        logoutListener.onLogout(message);
                        tokenManager.deleteToken();
                    }
                    else if(response.code()== 400){
                        APIMessage message = new Gson().fromJson(response.errorBody().charStream(), APIMessage.class);
                        Toast.makeText(context, message.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context, "Something Wrong!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<APIMessage> call, Throwable t) {

                }
            });
        }
        catch (Exception err){
            Toast.makeText(context, err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
