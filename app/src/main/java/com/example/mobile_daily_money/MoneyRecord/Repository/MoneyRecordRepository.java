package com.example.mobile_daily_money.MoneyRecord.Repository;

import android.content.Context;
import android.widget.Toast;

import com.example.mobile_daily_money.API.APIConfig;
import com.example.mobile_daily_money.API.APIMessage;
import com.example.mobile_daily_money.Authentication.TokenManager;
import com.example.mobile_daily_money.Authentication.TokenResponse;
import com.example.mobile_daily_money.MoneyRecord.Listener.CreateMoneyRecordListener;
import com.example.mobile_daily_money.MoneyRecord.Listener.GetMoneyRecordsListener;
import com.example.mobile_daily_money.MoneyRecord.Model.Request.MoneyRecordRequest;
import com.example.mobile_daily_money.MoneyRecord.Model.Response.MoneyRecords;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoneyRecordRepository {

    public static final void getMoneyRecords(TokenResponse tokenResponse, Context context, GetMoneyRecordsListener getMoneyRecordsListener){
        try{
            TokenManager tokenManager = TokenManager.getInstance(context.getSharedPreferences("Token",0));
            Call<MoneyRecords> moneyRecordsCall = APIConfig.getService(tokenManager).getMoneyRecords("Bearer "+tokenResponse.getToken());
            moneyRecordsCall.enqueue(new Callback<MoneyRecords>() {
                @Override
                public void onResponse(Call<MoneyRecords> call, Response<MoneyRecords> response) {
                    if(response.isSuccessful()){
                        MoneyRecords moneyRecords = response.body();
                        getMoneyRecordsListener.onGetMoneyRecords(moneyRecords);
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
                public void onFailure(Call<MoneyRecords> call, Throwable t) {

                }
            });
        }
        catch (Exception err){
            Toast.makeText(context, err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static final void createMoneyRecord(TokenResponse tokenResponse, MoneyRecordRequest moneyRecordRequest, Context context, CreateMoneyRecordListener createMoneyRecordListener){
        try {
            TokenManager tokenManager = TokenManager.getInstance(context.getSharedPreferences("Token",0));
            Call<APIMessage> callCreateMoneyRecord = APIConfig.getService(tokenManager).createMoneyRecord("Bearer "+tokenResponse.getToken(), moneyRecordRequest);
            callCreateMoneyRecord.enqueue(new Callback<APIMessage>() {
                @Override
                public void onResponse(Call<APIMessage> call, Response<APIMessage> response) {
                    if(response.isSuccessful()){
                        APIMessage message = response.body();
                        createMoneyRecordListener.onCreateMoneyRecord(message);
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

}
