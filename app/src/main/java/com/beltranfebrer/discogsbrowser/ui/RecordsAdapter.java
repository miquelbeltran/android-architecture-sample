package com.beltranfebrer.discogsbrowser.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beltranfebrer.discogsbrowser.R;
import com.beltranfebrer.discogsbrowser.network.model.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
public class RecordsAdapter extends RecyclerView.Adapter<RecordViewHolder> {

    List<Record> recordList = new ArrayList<>();

    public void addRecords(List<Record> recordList) {
        this.recordList.addAll(recordList);
        notifyDataSetChanged();
    }

    public void addRecord(Record record) {
        recordList.add(record);
        notifyDataSetChanged();
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_record, parent, false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        holder.textView.setText(recordList.get(position).instance_id);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public void cleanList() {
        recordList.clear();
        notifyDataSetChanged();
    }
}

class RecordViewHolder extends RecyclerView.ViewHolder {
    public final TextView textView;

    public RecordViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }
}
