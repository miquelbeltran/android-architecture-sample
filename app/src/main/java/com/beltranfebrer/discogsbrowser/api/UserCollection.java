package com.beltranfebrer.discogsbrowser.api;

import com.beltranfebrer.discogsbrowser.api.model.Record;
import com.beltranfebrer.discogsbrowser.api.model.RecordCollection;

import rx.Observer;
import rx.Scheduler;
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
    private Scheduler observeOnScheduler;

    public UserCollection(DiscogsService service, String username, Scheduler observeOnScheduler) {
        this.service = service;
        this.subject = ReplaySubject.create();
        this.observeOnScheduler = observeOnScheduler;
        getRecordsFromService(username);
    }

    private void getRecordsFromService(String user) {
        service.listRecords(user)
                .subscribeOn(Schedulers.io())
                .observeOn(observeOnScheduler)
                .subscribe(new Observer<RecordCollection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        subject.onError(e);
                    }

                    @Override
                    public void onNext(RecordCollection recordCollection) {
                        for (Record record : recordCollection.getRecords()) {
                            subject.onNext(record);
                        }
                    }
                });
    }
}
