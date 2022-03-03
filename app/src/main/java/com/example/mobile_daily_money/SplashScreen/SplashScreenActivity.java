package com.example.mobile_daily_money.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.mobile_daily_money.Authentication.TokenManager;
import com.example.mobile_daily_money.MoneyRecord.Activity.DashboardMoneyRecordActivity;
import com.example.mobile_daily_money.R;
import com.example.mobile_daily_money.User.Activity.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private SharedPreferences getSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TokenManager tokenManager = TokenManager.getInstance(getSharedPreferences("Token",0));
                if(tokenManager.isLogin()){
                    startActivity(new Intent(getApplicationContext(), DashboardMoneyRecordActivity.class));
                    finish();
                }
                else{
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        }, 2000);
    }
}