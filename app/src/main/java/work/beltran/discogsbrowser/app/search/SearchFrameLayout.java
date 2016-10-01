package work.beltran.discogsbrowser.app.search;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.eyeem.recyclerviewtools.adapter.WrapAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.app.common.RecordViewModel;
import work.beltran.discogsbrowser.app.common.RecordsAdapter;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class SearchFrameLayout extends FrameLayout implements SearchView {

    @BindView(R.id.recycler_records)
    RecyclerView recyclerView;

    Header header = new Header();
    Footer footer = new Footer();
    private RecordsAdapter adapter;
    private SearchPresenter presenter;

    public SearchFrameLayout(Context context) {
        super(context);
        init();
    }

    public SearchFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.view_collection, this);
        ButterKnife.bind(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Inject
    public void setPresenterAdapter(SearchPresenter presenter,
                                    RecordsAdapter adapter) {
        this.adapter = adapter;
        createHeaderFooter(adapter);
        this.presenter = presenter;
        presenter.attachView(this);
    }

    private void createHeaderFooter(RecordsAdapter adapter) {
        WrapAdapter wrapAdapter = new WrapAdapter(adapter);
        recyclerView.setAdapter(wrapAdapter);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_search, recyclerView, false);
        ButterKnife.bind(this.header, header);
        wrapAdapter.addHeader(header);
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.footer, recyclerView, false);
        ButterKnife.bind(this.footer, footer);
        wrapAdapter.addFooter(footer);
        this.header.searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void setLoading(boolean loading) {
        footer.progressBar.setVisibility(loading ? VISIBLE : GONE);
    }

    @Override
    public void display(List<RecordViewModel> records) {
        adapter.addItems(records);
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    public void displayError(@StringRes int messageId) {

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

    public static class Header {
        @BindView(R.id.search)
        android.widget.SearchView searchView;
    }

    public static class Footer {
        @BindView(R.id.progressbar)
        ProgressBar progressBar;
    }
}
