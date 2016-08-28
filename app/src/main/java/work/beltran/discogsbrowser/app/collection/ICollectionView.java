package work.beltran.discogsbrowser.app.collection;

import java.util.List;

import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.app.base.BaseView;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public interface ICollectionView extends BaseView {
    void addRecords(List<Record> records);

    void display(UserProfile userProfile);

    void setLoading(boolean loading);
}
