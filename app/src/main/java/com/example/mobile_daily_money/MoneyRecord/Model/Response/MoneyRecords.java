package com.example.mobile_daily_money.MoneyRecord.Model.Response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoneyRecords implements Parcelable {
    @SerializedName("moneyRecords")
    private ArrayList<MoneyRecord> moneyRecordArrayList;
    private int totalAmount;

    protected MoneyRecords(Parcel in) {
        moneyRecordArrayList = in.createTypedArrayList(MoneyRecord.CREATOR);
        totalAmount = in.readInt();
    }

    public static final Creator<MoneyRecords> CREATOR = new Creator<MoneyRecords>() {
        @Override
        public MoneyRecords createFromParcel(Parcel in) {
            return new MoneyRecords(in);
        }

        @Override
        public MoneyRecords[] newArray(int size) {
            return new MoneyRecords[size];
        }
    };

    public ArrayList<MoneyRecord> getMoneyRecordArrayList() {
        return moneyRecordArrayList;
    }

    public void setMoneyRecordArrayList(ArrayList<MoneyRecord> moneyRecordArrayList) {
        this.moneyRecordArrayList = moneyRecordArrayList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(moneyRecordArrayList);
        parcel.writeInt(totalAmount);
    }
}
