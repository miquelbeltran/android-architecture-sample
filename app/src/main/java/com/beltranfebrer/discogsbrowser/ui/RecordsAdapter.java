package com.beltranfebrer.discogsbrowser.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beltranfebrer.discogsbrowser.R;
import com.beltranfebrer.discogsbrowser.UserCollection;
import com.beltranfebrer.discogsbrowser.model.Record;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
public class RecordsAdapter extends RecyclerView.Adapter<RecordViewHolder> {
    private static final String TAG = RecordsAdapter.class.getCanonicalName();
    private UserCollection userCollection;
    private List<Record> recordList = new ArrayList<>();

    @Inject
    public void setUserCollection(UserCollection userCollection) {
        this.userCollection = userCollection;
        subscribe();
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
        holder.textView.setText(recordList.get(position).getInstance_id());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    private void subscribe() {
        userCollection.subject.subscribe(new Observer<Record>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError() " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(Record record) {
                recordList.add(record);
                notifyDataSetChanged();
            }
        });
    }
}

class RecordViewHolder extends RecyclerView.ViewHolder {
    public final TextView textView;

    public RecordViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.textView);
    }
}
