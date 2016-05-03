package work.beltran.discogsbrowser.ui.collection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.extras.PicassoOnScrollListener;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.App;

public class CollectionFragment extends Fragment implements LoadMoreOnScrollListener.Listener {
    private RecordsAdapter adapter;

    public CollectionFragment() {
        // Required empty public constructor
    }

    @Inject
    public void setAdapter(RecordsAdapter adapter) {
        this.adapter = adapter;
    }

    public static CollectionFragment newInstance() {
        CollectionFragment fragment = new CollectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getApiComponent().inject(this);
        ((App) getActivity().getApplication()).getAppComponent().inject(adapter);
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.records_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new LoadMoreOnScrollListener(this));
            recyclerView.addOnScrollListener(new PicassoOnScrollListener(adapter));
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onLoadMore(RecyclerView recyclerView) {
        ((RecordsAdapter) recyclerView.getAdapter()).loadMore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        initRecyclerView(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.activityOnDestroy();
    }
}
