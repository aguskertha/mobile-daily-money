package com.example.mobile_daily_money.User.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mobile_daily_money.MoneyRecord.Activity.DashboardMoneyRecordActivity;
import com.example.mobile_daily_money.User.Listener.LoginListener;
import com.example.mobile_daily_money.User.Model.Response.LoginResponse;
import com.example.mobile_daily_money.User.ViewModel.UserViewModel;
import com.example.mobile_daily_money.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private ActivityLoginBinding binding;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        onButtonLoginClicked();
        onTextButtonRegisterClicked();
    }

    public void onButtonLoginClicked(){
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserViewModel.login(binding, LoginActivity.this::onLogin);
            }
        });
    }

    public void onTextButtonRegisterClicked(){
        binding.tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onLogin(LoginResponse loginResponse) {
        Intent intent = new Intent(this, DashboardMoneyRecordActivity.class);
        startActivity(intent);
        finish();
    }
}