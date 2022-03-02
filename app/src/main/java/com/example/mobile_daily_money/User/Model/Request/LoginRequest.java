package com.example.mobile_daily_money.User.Model.Request;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginRequest {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
