package com.example.mobile_daily_money.MoneyRecord.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mobile_daily_money.API.APIMessage;
import com.example.mobile_daily_money.Authentication.GenerateToken;
import com.example.mobile_daily_money.Authentication.TokenListener;
import com.example.mobile_daily_money.Authentication.TokenResponse;
import com.example.mobile_daily_money.MoneyRecord.Adapter.MoneyRecordAdapter;
import com.example.mobile_daily_money.MoneyRecord.Listener.CreateMoneyRecordListener;
import com.example.mobile_daily_money.MoneyRecord.Listener.GetMoneyRecordsListener;
import com.example.mobile_daily_money.MoneyRecord.Model.Response.MoneyRecord;
import com.example.mobile_daily_money.MoneyRecord.Model.Response.MoneyRecords;
import com.example.mobile_daily_money.MoneyRecord.ViewModel.MoneyRecordViewModel;
import com.example.mobile_daily_money.Utils.MoneyFormat;
import com.example.mobile_daily_money.databinding.ActivityDashboardMoneyRecordBinding;

import java.util.List;

public class DashboardMoneyRecordActivity extends AppCompatActivity implements TokenListener, GetMoneyRecordsListener, CreateMoneyRecordListener {
    private ActivityDashboardMoneyRecordBinding binding;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardMoneyRecordBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        setToolbar();
        onFloatingButtonCreateClicked();
        GenerateToken.getToken(view.getContext(), this::onToken);
    }

    public void setToolbar(){
        setSupportActionBar(binding.toolbarMoneyRecord);
        getSupportActionBar().setTitle("Money Record");
    }

    @Override
    public void onToken(TokenResponse tokenResponse) {
        MoneyRecordViewModel.getMoneyRecords(binding, this::onGetMoneyRecords, tokenResponse);
        Toast.makeText(view.getContext(), tokenResponse.getToken(), Toast.LENGTH_SHORT);
    }

    @Override
    public void onGetMoneyRecords(MoneyRecords moneyRecords) {
        setRecycleView((List)moneyRecords.getMoneyRecordArrayList());
        setTotalAmount(moneyRecords.getTotalAmount());
    }

    public void setTotalAmount(int totalAmount){
        binding.tvRecordTotalAmount.setText(MoneyFormat.toRupiah(totalAmount));
    }

    public void setRecycleView(List<MoneyRecord> moneyRecordList){
        MoneyRecordAdapter moneyRecordAdapter = new MoneyRecordAdapter(moneyRecordList);
        binding.rvMoneyRecord.setLayoutManager(new LinearLayoutManager(this));
        binding.rvMoneyRecord.setAdapter(moneyRecordAdapter);
    }

    public void onFloatingButtonCreateClicked(){
        binding.fabRecordCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateMoneyRecordBottomSheet.newInstance(DashboardMoneyRecordActivity.this::onCreateMoneyRecord).show(getSupportFragmentManager(), CreateMoneyRecordBottomSheet.TAG);
            }
        });
    }

    @Override
    public void onCreateMoneyRecord(APIMessage message) {
        Toast.makeText(view.getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DashboardMoneyRecordActivity.class);
        startActivity(intent);
        finish();
    }
}