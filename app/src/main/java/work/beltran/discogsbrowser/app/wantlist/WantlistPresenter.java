package work.beltran.discogsbrowser.app.wantlist;

import java.util.List;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.app.base.BasePresenterForAdapter;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.business.WantedInteractor;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class WantlistPresenter extends BasePresenterForAdapter<WantlistView> {

    private final WantedInteractor interactor;
    private ProfileInteractor profileInteractor;

    public WantlistPresenter(WantedInteractor interactor,
                             ProfileInteractor profileInteractor) {
        this.interactor = interactor;
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void attachView(WantlistView view) {
        super.attachView(view);
        loadMore();
        addSubscription(profileInteractor
                .getProfile()
                .subscribe(new Observer<UserProfile>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserProfile userProfile) {
                        if (getView() != null) {
                            getView().display(userProfile);
                        }
                    }
                }));
    }

    public void loadMore() {
        if (loading) return;
        if (page > totalPages) return;
        setLoading(true);
        addSubscription(interactor.getWanted(page)
                .doOnTerminate(() -> setLoading(false))
                .doOnCompleted(() -> page++)
                .subscribe(userWanted -> addRecords(userWanted.getRecords()),
                e -> e.printStackTrace()));
    }

    private void addRecords(List<Record> records) {
        if (getView() != null) {
            getView().addRecords(RecordAdapterItem.createRecordsList(records, false));
        }
    }
}
