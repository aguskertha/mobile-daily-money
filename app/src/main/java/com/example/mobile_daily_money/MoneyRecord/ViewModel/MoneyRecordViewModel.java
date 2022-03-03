package com.example.mobile_daily_money.MoneyRecord.ViewModel;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile_daily_money.Authentication.TokenResponse;
import com.example.mobile_daily_money.MoneyRecord.Listener.CreateMoneyRecordListener;
import com.example.mobile_daily_money.MoneyRecord.Listener.GetMoneyRecordsListener;
import com.example.mobile_daily_money.MoneyRecord.Model.Request.MoneyRecordRequest;
import com.example.mobile_daily_money.MoneyRecord.Repository.MoneyRecordRepository;
import com.example.mobile_daily_money.databinding.ActivityDashboardMoneyRecordBinding;

public class MoneyRecordViewModel {
    public static final void getMoneyRecords(ActivityDashboardMoneyRecordBinding binding, GetMoneyRecordsListener getMoneyRecordsListener, TokenResponse tokenResponse){
        try {
            MoneyRecordRepository.getMoneyRecords(tokenResponse, binding.getRoot().getContext(), getMoneyRecordsListener);
        }
        catch (Exception err){
            Toast.makeText(binding.getRoot().getContext(), err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public static final void createMoneyRecord(Context context, MoneyRecordRequest moneyRecordRequest, CreateMoneyRecordListener createMoneyRecordListener, TokenResponse tokenResponse){
        try {
            MoneyRecordRepository.createMoneyRecord(tokenResponse, moneyRecordRequest, context, createMoneyRecordListener);
        }
        catch (Exception err){
            Toast.makeText(context, err.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
