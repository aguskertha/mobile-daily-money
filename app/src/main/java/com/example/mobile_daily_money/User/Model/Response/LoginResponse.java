package com.example.mobile_daily_money.User.Model.Response;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginResponse implements Parcelable {

    private String userID;
    private String message;
    private String token;
    private String refreshToken;

    protected LoginResponse(Parcel in) {
        userID = in.readString();
        message = in.readString();
        token = in.readString();
        refreshToken = in.readString();
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userID);
        parcel.writeString(message);
        parcel.writeString(token);
        parcel.writeString(refreshToken);
    }
}
