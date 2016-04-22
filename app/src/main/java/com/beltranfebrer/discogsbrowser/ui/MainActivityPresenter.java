package com.beltranfebrer.discogsbrowser.ui;

import android.util.Log;

import com.beltranfebrer.discogsbrowser.network.CachedUserCollection;
import com.beltranfebrer.discogsbrowser.network.model.Record;

import rx.Observer;

/**
 * Created by miquel on 22.04.16.
 */
public class MainActivityPresenter {
    private static final String TAG = MainActivityPresenter.class.getCanonicalName();

    private CachedUserCollection api;
    private MainActivityView view;

    public MainActivityPresenter(CachedUserCollection api) {
        this.api = api;
    }

    public void setView(MainActivityView view) {
        this.view = view;
    }

    public void loadList() {
        view.showRecords(api.recordList);
        api.subject.subscribe(new Observer<Record>() {
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
                view.addRecord(record);
            }
        });
    }
}
