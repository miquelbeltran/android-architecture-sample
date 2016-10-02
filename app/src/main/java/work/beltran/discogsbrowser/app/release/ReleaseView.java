package work.beltran.discogsbrowser.app.release;

import work.beltran.discogsbrowser.app.base.BaseView;

/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
public interface ReleaseView extends BaseView {

    void setAddToCollectionVisible(boolean displayAdd);

    void setRemoveFromCollectionVisible(boolean b);
}

