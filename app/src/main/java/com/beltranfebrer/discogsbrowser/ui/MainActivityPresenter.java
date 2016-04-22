package com.beltranfebrer.discogsbrowser.ui;

import android.util.Log;

import com.beltranfebrer.discogsbrowser.network.DiscogsService;
import com.beltranfebrer.discogsbrowser.network.model.Record;
import com.beltranfebrer.discogsbrowser.network.model.RecordCollection;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by miquel on 22.04.16.
 */
public class MainActivityPresenter {
    private static final String TAG = MainActivityPresenter.class.getCanonicalName();

    private DiscogsService service;
    private MainActivityView view;

    public MainActivityPresenter(DiscogsService service) {
        this.service = service;
    }

    public void setView(MainActivityView view) {
        this.view = view;
    }

    public Observable<RecordCollection> getCollection(String user) {
        return service.listRecords(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void loadList() {
        service.listRecords("mike513")
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
                    }

                    @Override
                    public void onNext(RecordCollection recordCollection) {
                        Log.d(TAG, "Collection items " + recordCollection.getPagination().getItems());
                        view.showRecords(recordCollection.getRecords());
                        for (Record repo : recordCollection.getRecords()) {
                            Log.d(TAG, "instance_id: " + repo.instance_id);
                        }
                    }
                });
    }
}
