package com.example.mobile_daily_money.Utils;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormat {
    public static final String toRupiah(int amount){
        Double tempPrice = Double.parseDouble(String.valueOf(amount));
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(tempPrice);
    }
}
