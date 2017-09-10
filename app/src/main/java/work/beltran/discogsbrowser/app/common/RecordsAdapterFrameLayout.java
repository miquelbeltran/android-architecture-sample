package work.beltran.discogsbrowser.app.common;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;

import java.util.List;

import butterknife.BindView;
import work.beltran.discogsbrowser.R;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
public abstract class RecordsAdapterFrameLayout extends FrameLayout
        implements LoadMoreOnScrollListener.Listener {
    private static final String STATE_LAYOUT_MANAGER = "STATE_LAYOUT_MANAGER";
    private static final String STATE_ADAPTER = "STATE_ADAPTER";
    private static final String STATE_PRESENTER = "STATE_PRESENTER";
    private static final String STATE_SUPER = "STATE_SUPER";
    private static final String STATE_HEADER = "STATE_HEADER";
    protected RecordsAdapter adapter;
    @BindView(R.id.recycler_records)
    public RecyclerView recyclerView;

    public RecordsAdapterFrameLayout(Context context) {
        super(context);
    }


    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
//        bundle.putParcelableArrayList(STATE_ADAPTER, (ArrayList<RecordItem>) adapter.getItems());
        bundle.putParcelable(STATE_LAYOUT_MANAGER, recyclerView.getLayoutManager().onSaveInstanceState());
        bundle.putBundle(STATE_HEADER, getHeaderState());
        return bundle;
    }

    protected abstract Bundle getHeaderState();

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            adapter.clear();
//            adapter.addItems(bundle.<RecordAdapterItem>getParcelableArrayList(STATE_ADAPTER));
            recyclerView.getLayoutManager().onRestoreInstanceState(bundle.getParcelable(STATE_LAYOUT_MANAGER));
            loadHeaderState(bundle.getBundle(STATE_HEADER));
            state = bundle.getParcelable(STATE_SUPER);
        }
        super.onRestoreInstanceState(state);
    }

    protected abstract void loadHeaderState(Bundle bundle);

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onLoadMore(RecyclerView recyclerView) {
    }

    public void addRecords(List<RecordItem> records) {
    }

    public void displayError(@StringRes int messageId) {

    }
}
