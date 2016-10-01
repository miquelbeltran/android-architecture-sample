package work.beltran.discogsbrowser.app.collection;

import java.util.List;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.app.base.BasePresenter;
import work.beltran.discogsbrowser.app.common.RecordViewModel;
import work.beltran.discogsbrowser.business.CollectionInteractor;
import work.beltran.discogsbrowser.business.ProfileInteractor;


/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class CollectionPresenter extends BasePresenter<CollectionView> {
    public static final String TAG = CollectionPresenter.class.getName();

    private CollectionInteractor interactor;
    private ProfileInteractor profileInteractor;
    private boolean loading;
    private Pagination pagination;

    public CollectionPresenter(CollectionInteractor interactor,
                               ProfileInteractor profileInteractor) {
        this.interactor = interactor;
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void attachView(CollectionView view) {
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
        addSubscription(interactor.getCollection(page)
                .subscribe(new Observer<UserCollection>() {
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
                    public void onNext(UserCollection userCollection) {
                        pagination = userCollection.getPagination();
                        List<RecordViewModel> records
                                = RecordViewModel.createRecordsList(userCollection.getRecords());
                        if (getView() != null) {
                            getView().addRecords(records);
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
