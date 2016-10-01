package work.beltran.discogsbrowser.app.base;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
public abstract class BasePresenterForAdapter<V extends BaseViewForAdapter> extends BasePresenter<V> {
    private static final String PAGE = "PAGE";
    private static final String TOTAL_PAGES = "TOTAL_PAGES";
    private static final String TAG = BasePresenterForAdapter.class.getName();
    protected boolean loading = false;
    protected int page = 1;
    protected int totalPages = 1;

    @Override
    public Bundle getStatus() {
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE, page);
        bundle.putInt(TOTAL_PAGES, totalPages);
        Log.d(TAG, "Save Status: " + page + " " + totalPages);
        return bundle;
    }

    @Override
    public void loadStatus(Bundle bundle) {
        page = bundle.getInt(PAGE, 1);
        totalPages = bundle.getInt(TOTAL_PAGES, 1);
        Log.d(TAG, "Load Status: " + page + " " + totalPages);
    }

    protected void setLoading(boolean loading) {
        this.loading = loading;
        if (getView() != null)
            getView().setLoading(loading);
    }

    public abstract void loadMore();
}
