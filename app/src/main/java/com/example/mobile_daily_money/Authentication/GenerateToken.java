package com.example.mobile_daily_money.Authentication;

import android.content.Context;

import com.example.mobile_daily_money.API.APIConfig;
import com.example.mobile_daily_money.MoneyRecord.Model.Response.MoneyRecords;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateToken {

    public static final void getToken(Context context, TokenListener tokenListener, int flag){
        try {
            TokenManager tokenManager = TokenManager.getInstance(context.getSharedPreferences("Token",0));
            TokenRequest tokenRequest = new TokenRequest(tokenManager.getRefreshToken(), tokenManager.getUserID());
            Call<TokenResponse> tokenResponseCall = APIConfig.getService(tokenManager).getToken(tokenRequest);
            tokenResponseCall.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    if(response.isSuccessful()){
                        TokenResponse tokenResponse = response.body();
                        tokenListener.onToken(flag, tokenResponse);
                    }
                    if (response.code() == 400 ){

                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {

                }
            });
        }
        catch (Exception err){

        }
    }

}
