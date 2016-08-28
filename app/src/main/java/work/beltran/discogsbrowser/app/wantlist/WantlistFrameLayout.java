package work.beltran.discogsbrowser.app.wantlist;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.adapter.WrapAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.app.common.RecordsAdapter;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class WantlistFrameLayout extends FrameLayout implements WantlistView, LoadMoreOnScrollListener.Listener {

    @BindView(R.id.recycler_records)
    RecyclerView recyclerView;

    Header header = new Header();
    Footer footer = new Footer();

    private RecordsAdapter adapter;
    private WantlistPresenter presenter;

    @Inject
    public void setPresenterAdapter(WantlistPresenter presenter, RecordsAdapter adapter) {
        this.adapter = adapter;
        createHeaderFooter(adapter);
        this.presenter = presenter;
        presenter.attachView(this);
    }

    public WantlistFrameLayout(Context context) {
        super(context);
        init();
    }

    public WantlistFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.view_collection, this);
        ButterKnife.bind(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new LoadMoreOnScrollListener(this));
    }

    @Override
    public void displayError(@StringRes int messageId) {

    }

    @Override
    public void addRecords(List<Record> records) {
        adapter.addItems(records);
    }

    @Override
    public void display(UserProfile userProfile) {
        header.textWantlist.setText(
                getResources().getString(
                        R.string.user_wantlist,
                        userProfile.getUsername()));
        header.textWantlistcount.setText(
                getResources().getString(
                        R.string.in_wantlist,
                        userProfile.getNum_wantlist()));
    }

    private void createHeaderFooter(RecordsAdapter adapter) {
        WrapAdapter wrapAdapter = new WrapAdapter(adapter);
        recyclerView.setAdapter(wrapAdapter);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_wantlist, recyclerView, false);
        ButterKnife.bind(this.header, header);
        wrapAdapter.addHeader(header);
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.footer, recyclerView, false);
        ButterKnife.bind(this.footer, footer);
        wrapAdapter.addFooter(footer);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.attachView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.detachView();
    }

    @Override
    public void onLoadMore(RecyclerView recyclerView) {
        presenter.loadMore();
    }

    @Override
    public void setLoading(boolean loading) {
        footer.progressBar.setVisibility(loading ? VISIBLE : GONE);
    }

    public static class Header {
        @BindView(R.id.text_wantlist)
        TextView textWantlist;
        @BindView(R.id.text_wantlist_count)
        TextView textWantlistcount;
    }

    public static class Footer {
        @BindView(R.id.progressbar)
        ProgressBar progressBar;
    }
}
