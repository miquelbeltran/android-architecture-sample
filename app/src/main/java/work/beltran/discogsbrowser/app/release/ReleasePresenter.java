package work.beltran.discogsbrowser.app.release;

import android.os.Bundle;
import android.util.Log;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.app.base.BasePresenter;
import work.beltran.discogsbrowser.business.CollectionInteractor;

/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
public class ReleasePresenter extends BasePresenter<ReleaseView> {

    private static final String TAG = ReleasePresenter.class.getName();
    private CollectionInteractor interactor;
    private int releaseId;

    public ReleasePresenter(CollectionInteractor interactor) {
        this.interactor = interactor;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
        addSubscription(interactor
                .getRecord(releaseId)
                .subscribe(new Observer<UserCollection>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getLocalizedMessage(), e);
                    }

                    @Override
                    public void onNext(UserCollection userCollection) {
                        setAddOrRemoveButton(userCollection.getRecords().isEmpty());
                    }
                }));
    }

    private void setAddOrRemoveButton(boolean displayAdd) {
        if (getView() != null) {
            getView().setAddToCollectionVisible(displayAdd);
            getView().setRemoveFromCollectionVisible(!displayAdd);
        }
    }

    @Override
    public Bundle getStatus() {
        return null;
    }

    @Override
    public void loadStatus(Bundle bundle) {

    }
}
