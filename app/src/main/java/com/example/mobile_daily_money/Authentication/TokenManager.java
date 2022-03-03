package com.example.mobile_daily_money.Authentication;

import android.content.SharedPreferences;

import com.example.mobile_daily_money.User.Model.Response.LoginResponse;

public class TokenManager {
    static private SharedPreferences prefs;
    static private SharedPreferences.Editor editor;
    public static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(LoginResponse loginResponse){
        editor.putString("TOKEN", loginResponse.getToken()).commit();
        editor.putString("REFRESH_TOKEN", loginResponse.getRefreshToken()).commit();
        editor.putString("USER_ID", loginResponse.getUserID()).commit();
        editor.apply();
    }

    public boolean isLogin(){
        return (INSTANCE.getRefreshToken()!=null);
    }

    public void deleteToken(){
        editor.remove("TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
        editor.remove("USER_ID").commit();
        editor.apply();
    }

    public String getToken(){
        return prefs.getString("TOKEN",null);
    }

    public String getRefreshToken(){
        return prefs.getString("REFRESH_TOKEN",null);
    }
    public String getUserID(){
        return prefs.getString("USER_ID",null);
    }

}
