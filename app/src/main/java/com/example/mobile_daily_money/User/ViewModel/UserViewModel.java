package com.example.mobile_daily_money.User.ViewModel;

import android.widget.Toast;

import com.example.mobile_daily_money.User.Listener.LoginListener;
import com.example.mobile_daily_money.User.Listener.RegisterListener;
import com.example.mobile_daily_money.User.Model.Request.LoginRequest;
import com.example.mobile_daily_money.User.Model.Request.RegisterRequest;
import com.example.mobile_daily_money.User.Repository.UserRepository;
import com.example.mobile_daily_money.databinding.ActivityLoginBinding;
import com.example.mobile_daily_money.databinding.ActivityRegisterBinding;

public class UserViewModel {

    public static final void login(ActivityLoginBinding binding, LoginListener loginListener){
        try {
            String email = binding.edtLoginEmail.getText().toString().trim();
            String password = binding.edtLoginPassword.getText().toString().trim();
            LoginRequest loginRequest = new LoginRequest(email, password);
            UserRepository.login(loginRequest, binding.getRoot().getContext(), loginListener);
        }
        catch (Exception err){
            Toast.makeText(binding.getRoot().getContext(), err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static final void register(ActivityRegisterBinding binding, RegisterListener registerListener){
        try {
            String name = binding.edtRegisterName.getText().toString().trim();
            String email = binding.edtRegisterEmail.getText().toString().trim();
            String password = binding.edtRegisterPassword.getText().toString().trim();
            String confirmPassword = binding.edtRegisterConfirmPassword.getText().toString().trim();
            if(!password.equals(confirmPassword)){
                throw new Exception("Password not matched!");
            }
            RegisterRequest registerRequest = new RegisterRequest(name, email, password);
            UserRepository.register(registerRequest, binding.getRoot().getContext(), registerListener);
        }
        catch (Exception err){
            Toast.makeText(binding.getRoot().getContext(), err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static final void loginAfterRegister(ActivityRegisterBinding binding, LoginListener loginListener){
        try {
            String email = binding.edtRegisterEmail.getText().toString().trim();
            String password = binding.edtRegisterPassword.getText().toString().trim();
            LoginRequest loginRequest = new LoginRequest(email, password);
            UserRepository.login(loginRequest, binding.getRoot().getContext(), loginListener);
        }
        catch (Exception err){
            Toast.makeText(binding.getRoot().getContext(), err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
