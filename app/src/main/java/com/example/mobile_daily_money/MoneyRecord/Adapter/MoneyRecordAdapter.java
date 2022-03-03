package com.example.mobile_daily_money.MoneyRecord.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_daily_money.MoneyRecord.Model.Response.MoneyRecord;
import com.example.mobile_daily_money.R;
import com.example.mobile_daily_money.Utils.MoneyFormat;

import java.util.List;

public class MoneyRecordAdapter extends RecyclerView.Adapter<MoneyRecordAdapter.MoneyRecordViewHolder> {
    private List<MoneyRecord> moneyRecordList;
    private View view;

    public MoneyRecordAdapter(List<MoneyRecord> moneyRecordList){
        this.moneyRecordList = moneyRecordList;
    }

    @NonNull
    @Override
    public MoneyRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_money_record, parent, false);
        return new MoneyRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyRecordViewHolder holder, int position) {
        try {
            MoneyRecord moneyRecord = moneyRecordList.get(position);
            holder.tvRecordDate.setText(moneyRecord.getDate());
            holder.tvRecordAmount.setText(MoneyFormat.toRupiah(moneyRecord.getAmount()));
            holder.tvRecordDescription.setText(moneyRecord.getDescription());
        }
        catch (Exception err){
            Toast.makeText(view.getContext(), err.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return moneyRecordList.size();
    }

    public class MoneyRecordViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRecordDate;
        private TextView tvRecordDescription;
        private TextView tvRecordAmount;

        public MoneyRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecordDate = itemView.findViewById(R.id.tv_record_date);
            tvRecordAmount = itemView.findViewById(R.id.tv_record_amount);
            tvRecordDescription = itemView.findViewById(R.id.tv_record_description);
        }
    }
}
