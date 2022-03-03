package com.example.mobile_daily_money.MoneyRecord.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobile_daily_money.Authentication.GenerateToken;
import com.example.mobile_daily_money.Authentication.TokenListener;
import com.example.mobile_daily_money.Authentication.TokenResponse;
import com.example.mobile_daily_money.MoneyRecord.Listener.CreateMoneyRecordListener;
import com.example.mobile_daily_money.MoneyRecord.Model.Request.MoneyRecordRequest;
import com.example.mobile_daily_money.MoneyRecord.ViewModel.MoneyRecordViewModel;
import com.example.mobile_daily_money.R;
import com.example.mobile_daily_money.Utils.FlagStorage;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateMoneyRecordBottomSheet extends BottomSheetDialogFragment implements TokenListener {
    public static final String TAG = "CreateMoneyRecord";
    private EditText edtRecordCreateAmount;
    private EditText edtRecordCreateDescription;
    private Button btnRecordCreateSave;
    private CreateMoneyRecordListener createMoneyRecordListener;
    private View view;
    public CreateMoneyRecordBottomSheet(CreateMoneyRecordListener createMoneyRecordListener){
        this.createMoneyRecordListener  = createMoneyRecordListener;
    }

    public static CreateMoneyRecordBottomSheet newInstance(CreateMoneyRecordListener createMoneyRecordListener){
        return new CreateMoneyRecordBottomSheet(createMoneyRecordListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.layout_create_money_record, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtRecordCreateAmount = view.findViewById(R.id.edt_record_create_amount);
        edtRecordCreateDescription = view.findViewById(R.id.edt_record_create_description);
        btnRecordCreateSave = view.findViewById(R.id.btn_record_create_save);
        onButtonSaveClicked();
    }

    public void onButtonSaveClicked(){
        btnRecordCreateSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateToken.getToken(view.getContext(), CreateMoneyRecordBottomSheet.this::onToken, FlagStorage.TOKEN_CREATE_RECORD);
            }
        });
    }

    @Override
    public void onToken(int flag, TokenResponse tokenResponse) {

        switch (flag){
            case FlagStorage.TOKEN_CREATE_RECORD:
                int amount = Integer.parseInt(edtRecordCreateAmount.getText().toString().trim());
                String description = edtRecordCreateDescription.getText().toString().trim();
                MoneyRecordRequest moneyRecordRequest = new MoneyRecordRequest(amount, description);
                MoneyRecordViewModel.createMoneyRecord(view.getContext(), moneyRecordRequest, createMoneyRecordListener, tokenResponse);
                dismiss();
                break;
        }
    }
}
