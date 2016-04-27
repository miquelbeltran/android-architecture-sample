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
    private Scheduler subscribeOnScheduler;

    public UserCollection(DiscogsService service, String username, Scheduler observeOnScheduler, Scheduler subscribeOnScheduler) {
        this.service = service;
        this.subject = ReplaySubject.create();
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
        getRecordsFromService(username, 1);
    }

    private void getRecordsFromService(final String user, final int page) {
        service.listRecords(user, page)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(new Observer<RecordCollection>() {
                    RecordCollection lastElem;

                    @Override
                    public void onCompleted() {
                        if (lastElem.getPagination().getPage() < lastElem.getPagination().getPages()) {
                            getRecordsFromService(user, page + 1);
                        } else {
                            subject.onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        subject.onError(e);
                    }

                    @Override
                    public void onNext(RecordCollection recordCollection) {
                        lastElem = recordCollection;
                        for (Record record : recordCollection.getRecords()) {
                            subject.onNext(record);
                        }
                    }
                });
    }

    public void loadMore() {

    }
}
