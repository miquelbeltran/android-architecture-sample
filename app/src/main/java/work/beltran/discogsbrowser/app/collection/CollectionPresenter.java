package work.beltran.discogsbrowser.app.collection;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.base.BasePresenter;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;
import work.beltran.discogsbrowser.business.CollectionInteractor;
import work.beltran.discogsbrowser.business.ProfileInteractor;


/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class CollectionPresenter extends BasePresenter<CollectionView> {
    public static final String TAG = CollectionPresenter.class.getName();
    private static final String PAGE = "PAGE";
    private static final String TOTAL_PAGES = "TOTAL_PAGES";

    private CollectionInteractor interactor;
    private ProfileInteractor profileInteractor;
    private boolean loading = false;
    private int page = 1;
    private int totalPages = 1;

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

    @Override
    public Bundle getStatus() {
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE, page);
        bundle.putInt(TOTAL_PAGES, totalPages);
        Log.d(TAG, "Save Status: " + page + " " + totalPages);
        return bundle;
    }

    @Override
    public void loadStatus(Bundle bundle) {
        page = bundle.getInt(PAGE, 1);
        totalPages = bundle.getInt(TOTAL_PAGES, 1);
        Log.d(TAG, "Load Status: " + page + " " + totalPages);
    }

    @Override
    public void loadMore() {
        Log.d(TAG, "Load More called. Page: " + page + ", total: " + totalPages);
        if (loading) return;
        if (page > totalPages) return;
        setLoading(true);
        addSubscription(interactor.getCollection(page)
                .subscribe(new Observer<UserCollection>() {
                    @Override
                    public void onCompleted() {
                        page++;
                        setLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setLoading(true);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserCollection userCollection) {
                        totalPages = userCollection.getPagination().getPages();
                        List<RecordAdapterItem> records
                                = RecordAdapterItem.createRecordsList(userCollection.getRecords());
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
