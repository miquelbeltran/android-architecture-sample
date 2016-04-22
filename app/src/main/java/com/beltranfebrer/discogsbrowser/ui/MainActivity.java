package com.beltranfebrer.discogsbrowser.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beltranfebrer.discogsbrowser.App;
import com.beltranfebrer.discogsbrowser.R;
import com.beltranfebrer.discogsbrowser.network.model.Record;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private RecordsAdapter adapter = new RecordsAdapter();

    @Inject
    public MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getComponent().inject(this);
        presenter.setView(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.records_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        presenter.loadList();
    }

    @Override
    public void showRecords(List<Record> records) {
        adapter.addRecords(records);
    }

    @Override
    public void addRecord(Record record) {
        adapter.addRecord(record);
    }

    private class RecordsAdapter extends RecyclerView.Adapter<RecordViewHolder> {

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

    }

    private class RecordViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        public RecordViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
