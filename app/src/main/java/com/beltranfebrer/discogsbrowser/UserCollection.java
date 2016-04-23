package com.beltranfebrer.discogsbrowser;

import android.util.Log;

import com.beltranfebrer.discogsbrowser.network.DiscogsService;
import com.beltranfebrer.discogsbrowser.model.Record;
import com.beltranfebrer.discogsbrowser.model.RecordCollection;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltranfebrer.com
 */
public class UserCollection {
    public final ReplaySubject<Record> subject;

    private static final String TAG = UserCollection.class.getCanonicalName();
    private DiscogsService service;

    public UserCollection(DiscogsService service, String username) {
        this.service = service;
        this.subject = ReplaySubject.create();
        getRecordsFromService(username);
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
                        for (Record record : recordCollection.getRecords()) {
                            Log.d(TAG, "instance_id: " + record.getInstance_id());
                            subject.onNext(record);
                        }
                    }
                });
    }
}
