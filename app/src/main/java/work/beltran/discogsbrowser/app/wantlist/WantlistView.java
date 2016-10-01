package work.beltran.discogsbrowser.app.wantlist;

import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.base.BaseView;
import work.beltran.discogsbrowser.app.base.BaseViewForAdapter;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public interface WantlistView extends BaseView, BaseViewForAdapter {
    void display(UserProfile userProfile);
}
