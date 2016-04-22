package com.beltranfebrer.discogsbrowser.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beltranfebrer.discogsbrowser.App;
import com.beltranfebrer.discogsbrowser.R;
import com.beltranfebrer.discogsbrowser.network.DiscogsService;
import com.beltranfebrer.discogsbrowser.network.model.Record;
import com.beltranfebrer.discogsbrowser.network.model.RecordCollection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Inject
    public MainActivityPresenter presenter;

    List<Record> recordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getComponent().inject(this);
        presenter.setView(this);

        recyclerView = (RecyclerView) findViewById(R.id.records_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecordsAdapter());

        // Move this call to a refresh listener
        presenter.loadList();
    }

    @Override
    public void showRecords(List<Record> records) {
        recordList = records;
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private class RecordsAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.card_record, parent, false);

            return new RecordViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView.findViewById(R.id.textView);
            textView.setText(recordList.get(position).instance_id);
        }

        @Override
        public int getItemCount() {
            return recordList.size();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder {
        public RecordViewHolder(View itemView) {
            super(itemView);
        }
    }
}
