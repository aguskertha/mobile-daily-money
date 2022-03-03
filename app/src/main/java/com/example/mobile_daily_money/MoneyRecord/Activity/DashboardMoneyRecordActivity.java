package com.example.mobile_daily_money.MoneyRecord.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.example.mobile_daily_money.R;
import com.example.mobile_daily_money.User.Activity.LoginActivity;
import com.example.mobile_daily_money.User.Listener.LogoutListener;
import com.example.mobile_daily_money.User.Repository.UserRepository;
import com.example.mobile_daily_money.Utils.FlagStorage;
import com.example.mobile_daily_money.Utils.MoneyFormat;
import com.example.mobile_daily_money.databinding.ActivityDashboardMoneyRecordBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class DashboardMoneyRecordActivity extends AppCompatActivity implements TokenListener, GetMoneyRecordsListener, CreateMoneyRecordListener, LogoutListener {
    private ActivityDashboardMoneyRecordBinding binding;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardMoneyRecordBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);
        setToolbar();
        setDrawerLayout();
        setNavigationDrawer();
        onFloatingButtonCreateClicked();
        GenerateToken.getToken(view.getContext(), this::onToken, FlagStorage.TOKEN_GET_RECORDS);

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setNavigationDrawer(){
        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_logout:
                        GenerateToken.getToken(view.getContext(), DashboardMoneyRecordActivity.this::onToken, FlagStorage.TOKEN_LOGOUT);
                        break;
                }
                return false;
            }
        });
    }

    public void setDrawerLayout(){
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.myDrawerLayout, R.string.nav_open, R.string.nav_close);
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setToolbar(){
        setSupportActionBar(binding.toolbarMoneyRecord);
        getSupportActionBar().setTitle("Money Record");
    }

    @Override
    public void onToken(int flag, TokenResponse tokenResponse) {
        switch (flag){
            case FlagStorage.TOKEN_GET_RECORDS:
                MoneyRecordViewModel.getMoneyRecords(binding, this::onGetMoneyRecords, tokenResponse);
                break;
            case FlagStorage.TOKEN_LOGOUT:
                UserRepository.logout(view.getContext(), tokenResponse, this::onLogout);
                break;
        }
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

    @Override
    public void onLogout(APIMessage message) {
        Toast.makeText(view.getContext(), message.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}