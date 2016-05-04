package work.beltran.discogsbrowser.api;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Func1;
import rx.subjects.ReplaySubject;
import work.beltran.discogsbrowser.api.model.Record;
import work.beltran.discogsbrowser.api.model.RecordsResult;
import work.beltran.discogsbrowser.api.model.UserIdentity;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public abstract class RecordsList<T extends RecordsResult> {
    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;
    private ReplaySubject<Record> subject;
    protected DiscogsService service;
    private Observable<UserIdentity> userIdentityObservable;
    private int nextPage;
    private int totalPages;
    private List<Boolean> completedPages;

    protected RecordsList(DiscogsService service,
                          Observable<UserIdentity> userIdentityObservable,
                          Scheduler subscribeOnScheduler,
                          Scheduler observeOnScheduler) {
        this.service = service;
        this.userIdentityObservable = userIdentityObservable;
        this.subject = ReplaySubject.create();
        this.nextPage = 1;
        this.totalPages = 1;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    public ReplaySubject<Record> getSubject() {
        return subject;
    }

    public void loadMoreData() {
        if (nextPage <= totalPages) {
            getRecordsFromService(this.nextPage++);
        }
    }

    private void getRecordsFromService(final int nextPage) {
        userIdentityObservable.flatMap(new Func1<UserIdentity, Observable<T>>() {
            @Override
            public Observable<T> call(UserIdentity userIdentity) {
                return serviceCallToGetRecords(userIdentity, nextPage)
                        .subscribeOn(subscribeOnScheduler);
            }
        })
                .observeOn(observeOnScheduler)
                .subscribe(new Observer<T>() {
                    RecordsResult result;

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        subject.onError(e);
                    }

                    @Override
                    public void onNext(RecordsResult recordCollection) {
                        totalPages = recordCollection.getPagination().getPages();
                        if (recordCollection.getRecords() != null) {
                            for (Record record : recordCollection.getRecords()) {
                                subject.onNext(record);
                            }
                        }
                    }
                });
    }

    protected abstract Observable<T> serviceCallToGetRecords(UserIdentity userIdentity, int nextPage);
}
