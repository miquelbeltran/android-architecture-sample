package work.beltran.discogsbrowser.app.collection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.adapter.WrapAdapter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.common.RecordsAdapter;
import work.beltran.discogsbrowser.app.common.RecordsAdapterFrameLayout;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
@SuppressLint("ViewConstructor")
public class CollectionFrameLayout extends RecordsAdapterFrameLayout<CollectionPresenter>
        implements CollectionView {

    CollectionHeader header = new CollectionHeader();
    Footer footer = new Footer();

    @Inject
    public Picasso picasso;

    public CollectionFrameLayout(Context context, int id) {
        super(context);
        setId(id);
        init();
    }

    @Inject
    public void setAdapter(RecordsAdapter adapter) {
        this.adapter = adapter;
        createHeaderFooter(adapter);
    }

    private void init() {
        View view = inflate(getContext(), R.layout.view_collection, this);
        ButterKnife.bind(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new LoadMoreOnScrollListener(this));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.attachView(this);
    }

    @Override
    public void display(UserProfile userProfile) {
        header.bind(userProfile, picasso, getContext());
    }

    @Override
    public void setLoading(boolean loading) {
        footer.progressBar.setVisibility(loading ? VISIBLE : GONE);
    }

    private void createHeaderFooter(RecordsAdapter adapter) {
        WrapAdapter wrapAdapter = new WrapAdapter(adapter);
        recyclerView.setAdapter(wrapAdapter);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header, recyclerView, false);
        ButterKnife.bind(this.header, header);
        wrapAdapter.addHeader(header);
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.footer, recyclerView, false);
        ButterKnife.bind(this.footer, footer);
        wrapAdapter.addFooter(footer);
    }

    @Inject
    @Override
    public void setPresenter(CollectionPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected Bundle getHeaderState() {
        return header.getState();
    }

    @Override
    protected void loadHeaderState(Bundle bundle) {
        header.loadState(bundle, picasso);
    }

    public void removeRecord(int releaseId) {
        adapter.removeItem(releaseId);

    }

    public static class Footer {
        @BindView(R.id.progressbar)
        ProgressBar progressBar;
    }
}
