package work.beltran.discogsbrowser.ui.main.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.adapter.WrapAdapter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.business.profile.ApiFrontend;

/**
 * Created by Miquel Beltran on 12.05.16.
 * More on http://beltran.work
 */
public abstract class RecordsFragment<T extends UserRecordsAdapter> extends Fragment implements LoadMoreOnScrollListener.Listener {
    protected T adapter;
    protected ApiFrontend collection;
    protected Picasso picasso;

    @Inject
    public void setAdapter(T adapter) {
        this.adapter = adapter;
    }

    @Inject
    public void setCollection(ApiFrontend collection) {
        this.collection = collection;
    }

    @Inject
    public void setPicasso(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(getTag(), adapter.getBundle());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            adapter.loadBundle(savedInstanceState.getBundle(getTag()));
        }
    }

    private void initRecyclerView(View view, LayoutInflater inflater) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.records_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new LoadMoreOnScrollListener(this));
            recyclerView.setLayoutManager(layoutManager);
            WrapAdapter wrapAdapter = new WrapAdapter(adapter);
            recyclerView.setAdapter(wrapAdapter);
            initHeaderFooter(inflater, recyclerView, wrapAdapter);
        }
    }

    protected abstract void injectDependencies();

    protected abstract void initHeaderFooter(LayoutInflater inflater, RecyclerView recyclerView, WrapAdapter wrapAdapter);

    @Override
    public void onLoadMore(RecyclerView recyclerView) {
        ((UserRecordsAdapter) ((WrapAdapter) recyclerView.getAdapter()).getWrapped()).loadMore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        initRecyclerView(view, inflater);
        if (savedInstanceState != null) {
            adapter.loadBundle(savedInstanceState.getBundle(getTag()));
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.activityOnDestroy();
    }
}
