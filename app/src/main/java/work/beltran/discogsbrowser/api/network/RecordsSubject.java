package work.beltran.discogsbrowser.api.network;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Func1;
import rx.subjects.ReplaySubject;
import work.beltran.discogsbrowser.api.model.RecordsWithPagination;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 04.05.16.
 * More on http://beltran.work
 */
public abstract class RecordsSubject<T extends RecordsWithPagination> {
    private Scheduler observeOnScheduler;
    protected Scheduler subscribeOnScheduler;
    protected ReplaySubject<Record> subject;
    protected DiscogsService service;
    private Observable<UserIdentity> userIdentityObservable;
    protected int nextPage;
    protected int totalPages;
    private List<Boolean> completedPages;

    protected RecordsSubject(DiscogsService service,
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

    public synchronized void loadMoreData() {
        if (nextPage <= totalPages) {
            getRecordsFromService(this.nextPage++);
        }
    }

    protected void getRecordsFromService(final int nextPage) {
        userIdentityObservable
                .flatMap(new Func1<UserIdentity, Observable<T>>() {
                    @Override
                    public Observable<T> call(UserIdentity userIdentity) {
                        return serviceCallToGetRecords(userIdentity, nextPage)
                                .subscribeOn(subscribeOnScheduler);
                    }
                })
                .observeOn(observeOnScheduler)
                .subscribe(new Observer<T>() {
                    int totalItems;

                    @Override
                    public void onCompleted() {
                        if (subject.size() == totalItems) {
                            subject.onCompleted();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        subject.onError(e);
                    }

                    @Override
                    public void onNext(RecordsWithPagination recordCollection) {
                        totalPages = recordCollection.getPagination().getPages();
                        totalItems = recordCollection.getPagination().getItems();
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
