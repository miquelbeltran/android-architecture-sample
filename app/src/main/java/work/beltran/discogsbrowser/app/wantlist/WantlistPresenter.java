package work.beltran.discogsbrowser.app.wantlist;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.UserWanted;
import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.app.base.BasePresenter;
import work.beltran.discogsbrowser.app.common.RecordViewModel;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.business.WantedInteractor;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class WantlistPresenter extends BasePresenter<WantlistView> {

    private final WantedInteractor interactor;
    private ProfileInteractor profileInteractor;
    private boolean loading;
    private Pagination pagination;

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

    void loadMore() {
        if (loading) return;
        int page = 0;
        if (pagination != null) {
            if (pagination.getPage() >= pagination.getPages()) return;
            page = pagination.getPage() + 1;
        }
        setLoading(true);
        addSubscription(interactor.getWanted(page)
                .subscribe(new Observer<UserWanted>() {
                    @Override
                    public void onCompleted() {
                        setLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setLoading(true);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserWanted userWanted) {
                        if (getView() != null) {
                            getView().addRecords(RecordViewModel
                                    .createRecordsList(userWanted.getRecords()));
                        }
                    }
                }));
    }

    private void setLoading(boolean loading) {
        this.loading = loading;
        if (getView() != null)
            getView().setLoading(loading);
    }
}
