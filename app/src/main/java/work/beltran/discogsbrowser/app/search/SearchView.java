package work.beltran.discogsbrowser.app.search;

import java.util.List;

import work.beltran.discogsbrowser.app.base.BaseView;
import work.beltran.discogsbrowser.app.common.RecordViewModel;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public interface SearchView extends BaseView {
    void setLoading(boolean loading);

    void display(List<RecordViewModel> records);

    void clear();
}
