package work.beltran.discogsbrowser.app.base;

import java.util.List;

import work.beltran.discogsbrowser.app.common.RecordAdapterItem;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
public interface BaseViewForAdapter extends BaseView {
    void setLoading(boolean loading);
    void addRecords(List<RecordAdapterItem> records);
}
