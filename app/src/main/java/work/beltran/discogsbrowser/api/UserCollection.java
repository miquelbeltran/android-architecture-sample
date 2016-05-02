package work.beltran.discogsbrowser.api;

import work.beltran.discogsbrowser.api.model.Record;
import work.beltran.discogsbrowser.api.model.RecordCollection;

import rx.Observer;
import rx.Scheduler;
import rx.subjects.ReplaySubject;

/**
 * Created by Miquel Beltran on 22.04.16.
 * More on http://beltran.work
 */
public class UserCollection {

    private static final String TAG = UserCollection.class.getCanonicalName();
    private DiscogsService service;
    private Scheduler observeOnScheduler;
    private Scheduler subscribeOnScheduler;
    private String user;
    private int nextPage;
    private ReplaySubject<Record> subject;

    public UserCollection(DiscogsService service, String username, Scheduler observeOnScheduler, Scheduler subscribeOnScheduler) {
        this.service = service;
        this.subject = ReplaySubject.create();
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
        this.user = username;
        this.nextPage = 1;
        getRecordsFromService(nextPage);
    }

    public ReplaySubject<Record> getSubject() {
        return subject;
    }

    private void getRecordsFromService(int page) {
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
