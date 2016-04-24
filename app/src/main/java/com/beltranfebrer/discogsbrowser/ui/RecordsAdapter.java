package com.beltranfebrer.discogsbrowser.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beltranfebrer.discogsbrowser.R;
import com.beltranfebrer.discogsbrowser.api.UserCollection;
import com.beltranfebrer.discogsbrowser.api.model.Record;
import com.beltranfebrer.discogsbrowser.databinding.CardRecordBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordViewHolder> {
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
        CardRecordBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_record, parent, false);
        return new RecordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        holder.binding.setRecord(recordList.get(position));
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

    public class RecordViewHolder extends RecyclerView.ViewHolder {
        public final CardRecordBinding binding;

        public RecordViewHolder(CardRecordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
