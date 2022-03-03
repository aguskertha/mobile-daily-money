package com.example.mobile_daily_money.User.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mobile_daily_money.API.APIMessage;
import com.example.mobile_daily_money.MoneyRecord.Activity.DashboardMoneyRecordActivity;
import com.example.mobile_daily_money.R;
import com.example.mobile_daily_money.User.Listener.LoginListener;
import com.example.mobile_daily_money.User.Listener.RegisterListener;
import com.example.mobile_daily_money.User.Model.Response.LoginResponse;
import com.example.mobile_daily_money.User.ViewModel.UserViewModel;
import com.example.mobile_daily_money.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements RegisterListener, LoginListener {
    private ActivityRegisterBinding binding;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        super.onCreate(savedInstanceState);
        setContentView(view);
        onButtonRegisterClicked();
    }

    public void onButtonRegisterClicked(){
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserViewModel.register(binding, RegisterActivity.this::onRegister);
            }
        });
    }

    @Override
    public void onRegister(APIMessage message) {
        Toast.makeText(view.getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
        UserViewModel.loginAfterRegister(binding, this::onLogin);
    }

    @Override
    public void onLogin(LoginResponse loginResponse) {
        Intent intent = new Intent(this, DashboardMoneyRecordActivity.class);
        startActivity(intent);
        finish();
    }
}