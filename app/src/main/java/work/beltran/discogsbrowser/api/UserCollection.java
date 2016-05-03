package work.beltran.discogsbrowser.api;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.subjects.ReplaySubject;
import work.beltran.discogsbrowser.api.model.Record;
import work.beltran.discogsbrowser.api.model.RecordCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
public class UserCollection {

    private static final String TAG = UserCollection.class.getCanonicalName();
    private DiscogsService service;
    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;
    private int nextPage;
    private ReplaySubject<Record> subject;
    private Observable<UserIdentity> userIdentityObservable;

    public UserCollection(DiscogsService service, Scheduler observeOnScheduler, Scheduler subscribeOnScheduler) {
        this.service = service;
        this.subject = ReplaySubject.create();
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
        this.nextPage = 1;
        buildUserIndentityRequest();
        getRecordsFromService(nextPage);
    }

    private void buildUserIndentityRequest() {
        userIdentityObservable = service.getUserIdentity()
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .cache();
    }

    public Observable<UserIdentity> getUserIdentity() {
        return userIdentityObservable;
    }

    private void getRecordsFromService(final int nextPage) {
        userIdentityObservable.subscribe(new Observer<UserIdentity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        subject.onError(e);
                    }

                    @Override
                    public void onNext(UserIdentity userIdentity) {
                        getRecordsFromService(userIdentity.getUsername(), nextPage);
                    }
                });
    }

    public ReplaySubject<Record> getSubject() {
        return subject;
    }

    private void getRecordsFromService(String user, int page) {
        service.listRecords(user, page)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(new Observer<RecordCollection>() {
                    RecordCollection lastElem;

                    @Override
                    public void onCompleted() {
                        if (lastElem.getPagination().getPage() == lastElem.getPagination().getPages()) {
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
        if (!subject.hasCompleted()) {
            getRecordsFromService(++this.nextPage);
        }
    }
}
