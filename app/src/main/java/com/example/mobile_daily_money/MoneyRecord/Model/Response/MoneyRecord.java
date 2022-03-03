package com.example.mobile_daily_money.MoneyRecord.Model.Response;

import android.os.Parcel;
import android.os.Parcelable;

public class MoneyRecord implements Parcelable {
    private int amount;
    private String description;
    private String date;
    private String userID;

    protected MoneyRecord(Parcel in) {
        amount = in.readInt();
        description = in.readString();
        date = in.readString();
        userID = in.readString();
    }

    public static final Creator<MoneyRecord> CREATOR = new Creator<MoneyRecord>() {
        @Override
        public MoneyRecord createFromParcel(Parcel in) {
            return new MoneyRecord(in);
        }

        @Override
        public MoneyRecord[] newArray(int size) {
            return new MoneyRecord[size];
        }
    };

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(amount);
        parcel.writeString(description);
        parcel.writeString(date);
        parcel.writeString(userID);
    }
}
