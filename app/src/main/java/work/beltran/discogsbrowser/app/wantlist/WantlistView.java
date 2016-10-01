package work.beltran.discogsbrowser.app.wantlist;

import java.util.List;

import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.base.BaseView;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public interface WantlistView extends BaseView {
    void addRecords(List<RecordAdapterItem> records);

    void display(UserProfile userProfile);

    void setLoading(boolean loading);
}
