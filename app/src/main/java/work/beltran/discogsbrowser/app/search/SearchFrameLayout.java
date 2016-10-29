package work.beltran.discogsbrowser.app.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.eyeem.recyclerviewtools.adapter.WrapAdapter;
import com.google.zxing.integration.android.IntentIntegrator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.app.common.RecordsAdapter;
import work.beltran.discogsbrowser.app.common.RecordsAdapterFrameLayout;
import work.beltran.discogsbrowser.app.main.MainActivity;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
@SuppressLint("ViewConstructor")
public class SearchFrameLayout extends RecordsAdapterFrameLayout<SearchPresenter> implements SearchView {
    private static final String STATE_SEARCH = "STATE_SEARCH";
    Header header = new Header();
    Footer footer = new Footer();

    public SearchFrameLayout(Context context, int id) {
        super(context);
        setId(id);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.view_collection, this);
        ButterKnife.bind(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Inject
    public void setAdapter(RecordsAdapter adapter) {
        this.adapter = adapter;
        createHeaderFooter(adapter);
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
        this.header.scanButton.setOnClickListener(view -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator((MainActivity) getContext());
            intentIntegrator.initiateScan();
        });
    }

    @Override
    public void setLoading(boolean loading) {
        footer.progressBar.setVisibility(loading ? VISIBLE : GONE);
    }

    @Override
    public void clear() {
        adapter.clear();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.attachView(this);
    }

    @Inject
    @Override
    public void setPresenter(SearchPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected Bundle getHeaderState() {
        Bundle bundle = new Bundle();
        bundle.putString(STATE_SEARCH, header.searchView.getQuery().toString());
        return bundle;
    }

    @Override
    protected void loadHeaderState(Bundle bundle) {
        header.searchView.setQuery(bundle.getString(STATE_SEARCH), false);
    }

    public void searchWithBarcode(String contents) {
        header.searchView.setQuery(contents, true);
        presenter.search(contents);
    }

    public static class Header {
        @BindView(R.id.search)
        android.widget.SearchView searchView;
        @BindView(R.id.scanButton)
        ImageButton scanButton;
    }

    public static class Footer {
        @BindView(R.id.progressbar)
        ProgressBar progressBar;
    }
}
