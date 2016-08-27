package work.beltran.discogsbrowser.ui.collection;

import android.util.Log;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.business.CollectionInteractor;
import work.beltran.discogsbrowser.business.ProfileInteractor;
import work.beltran.discogsbrowser.ui.base.BasePresenter;


/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class CollectionPresenter extends BasePresenter<ICollectionView> {
    public static final String TAG = CollectionPresenter.class.getName();

    private CollectionInteractor interactor;
    private ProfileInteractor profileInteractor;

    public CollectionPresenter(CollectionInteractor interactor,
                               ProfileInteractor profileInteractor) {
        this.interactor = interactor;
        this.profileInteractor = profileInteractor;
    }

    @Override
    public void attachView(ICollectionView view) {
        super.attachView(view);
        addSubscription(interactor.getCollection(0)
                .subscribe(new Observer<UserCollection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(UserCollection userCollection) {
                        Log.d(TAG, "Got: " + userCollection.getRecords().size() + " records.");
                        if (getView() != null) {
                            getView().addRecords(userCollection.getRecords());
                        }
                    }
                }));
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
}
