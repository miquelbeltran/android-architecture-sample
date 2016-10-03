package work.beltran.discogsbrowser.app.release;

import android.os.Bundle;

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
    }

    @Override
    public Bundle getStatus() {
        return null;
    }

    @Override
    public void loadStatus(Bundle bundle) {

    }
}
