package com.example.mobile_daily_money.Authentication;

public class TokenRequest {
    private String refreshToken;
    private String userID;

    public TokenRequest(String refreshToken, String userID) {
        this.refreshToken = refreshToken;
        this.userID = userID;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
