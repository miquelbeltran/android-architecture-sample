package work.beltran.discogsbrowser.app.release;

import android.os.Bundle;

import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.app.base.BasePresenter;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;
import work.beltran.discogsbrowser.business.CollectionRepository;

/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
public class ReleasePresenter extends BasePresenter<ReleaseView> {

    private static final String TAG = "ReleasePresenter";
    private CollectionRepository interactor;
    private int releaseId;

    public ReleasePresenter(CollectionRepository interactor) {
        this.interactor = interactor;
    }

    public void setReleaseId(int releaseId) {
        this.releaseId = releaseId;
        refreshView(releaseId);
    }

    private void refreshView(int releaseId) {
        addSubscription(interactor
                .getRecord(releaseId)
                .subscribe(this::display, Throwable::printStackTrace));
    }

    private void display(UserCollection userCollection) {
        if (userCollection.getRecords().size() == 0) return;
        Record record = userCollection.getRecords().get(0);
        RecordAdapterItem recordAdapterItem = RecordAdapterItem.createRecordViewModel(record, true);
        if (getView() != null) {
            getView().display(recordAdapterItem);
        }
    }

    @Override
    public Bundle getStatus() {
        return null;
    }

    @Override
    public void loadStatus(Bundle bundle) {

    }

    public void addToWantlist() {

    }

    public void removeFromWantlist() {

    }

    public void removeFromCollection() {
        addSubscription(interactor
                .removeRecord(releaseId)
                .subscribe(Void -> removedFromCollection(), Throwable::printStackTrace));
    }

    private void removedFromCollection() {
        if (getView() != null) {
            getView().setRemoveFromCollectionVisible(false);
            getView().setAddToCollectionVisible(true);
            getView().broadcastRemoveFromCollection();
        }
    }

    public void addToCollection() {
        addSubscription(interactor
                .addRecord(releaseId)
                .subscribe(Void -> addedToCollection(), Throwable::printStackTrace));
    }

    private void addedToCollection() {
        if (getView() != null) {
            getView().setRemoveFromCollectionVisible(true);
            getView().setAddToCollectionVisible(false);
            getView().broadcastAddToCollection();
        }
    }
}
