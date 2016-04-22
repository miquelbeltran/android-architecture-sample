package com.beltranfebrer.discogsbrowser.network;

import android.util.Log;

import com.beltranfebrer.discogsbrowser.network.model.Record;
import com.beltranfebrer.discogsbrowser.network.model.RecordCollection;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
public class CachedUserCollection {
    private static final String TAG = CachedUserCollection.class.getCanonicalName();
    public List<Record> recordList = new ArrayList<>();
    public PublishSubject<Record> subject;

    private DiscogsService service;
    private String cachedUser;

    public CachedUserCollection(DiscogsService service) {
        this.service = service;
        this.subject = PublishSubject.create();
        getRecordsFromService("mike513");
    }

    private void getRecordsFromService(String user) {
        service.listRecords(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecordCollection>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError() " + e.getMessage());
                        e.printStackTrace();
                        subject.onError(e);
                    }

                    @Override
                    public void onNext(RecordCollection recordCollection) {
                        Log.d(TAG, "Collection items " + recordCollection.getPagination().getItems());
                        recordList.addAll(recordCollection.getRecords());
                        for (Record record : recordCollection.getRecords()) {
                            Log.d(TAG, "instance_id: " + record.instance_id);
                            subject.onNext(record);
                        }
                    }
                });
    }
}
