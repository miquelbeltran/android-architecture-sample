package work.beltran.discogsbrowser.app.collection;

import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.base.BaseViewForAdapter;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public interface CollectionView extends BaseViewForAdapter {
    void display(UserProfile userProfile);
}
