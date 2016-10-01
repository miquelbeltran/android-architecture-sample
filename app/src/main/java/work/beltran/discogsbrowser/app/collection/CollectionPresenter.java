package work.beltran.discogsbrowser.app.collection;

import android.util.Log;

import java.util.List;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.base.BasePresenterForAdapter;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;
import work.beltran.discogsbrowser.business.CollectionInteractor;
import work.beltran.discogsbrowser.business.ProfileInteractor;


/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class CollectionPresenter extends BasePresenterForAdapter<CollectionView> {
    public static final String TAG = CollectionPresenter.class.getName();
    private CollectionInteractor interactor;
    private ProfileInteractor profileInteractor;

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

}
