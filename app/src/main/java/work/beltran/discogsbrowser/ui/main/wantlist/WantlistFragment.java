package work.beltran.discogsbrowser.ui.main.wantlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.adapter.WrapAdapter;
import com.eyeem.recyclerviewtools.extras.PicassoOnScrollListener;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import rx.Observer;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.ui.App;

public class WantlistFragment extends Fragment implements LoadMoreOnScrollListener.Listener {
    private WantRecordsAdapter adapter;
    private ApiFrontend collection;
    private Picasso picasso;

    public WantlistFragment() {
        // Required empty public constructor
    }

    @Inject
    public void setAdapter(WantRecordsAdapter adapter) {
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

    public static WantlistFragment newInstance() {
        WantlistFragment fragment = new WantlistFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getApiComponent().inject(this);
        ((App) getActivity().getApplication()).getApiComponent().inject(adapter);
    }

    private void initRecyclerView(View view, LayoutInflater inflater) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.wantlist_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new LoadMoreOnScrollListener(this));
            recyclerView.addOnScrollListener(new PicassoOnScrollListener(adapter));
            recyclerView.setLayoutManager(layoutManager);
            WrapAdapter wrapAdapter = new WrapAdapter(adapter);
            recyclerView.setAdapter(wrapAdapter);
            initHeaderFooter(inflater, recyclerView, wrapAdapter);
        }
    }

    private void initHeaderFooter(LayoutInflater inflater, RecyclerView recyclerView, WrapAdapter wrapAdapter) {
        final View header = inflater.inflate(R.layout.header_wantlist, recyclerView, false);
        wrapAdapter.addHeader(header);
        collection.getUserProfile().subscribe(new Observer<UserProfile>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserProfile userProfile) {
                TextView userWantlist = (TextView) header.findViewById(R.id.textWantlist);
                userWantlist.setText(getResources().getString(R.string.user_wantlist, userProfile.getUsername()));
                TextView inWantList = (TextView) header.findViewById(R.id.textInWantlist);
                inWantList.setText(getResources().getString(R.string.in_wantlist, userProfile.getNum_wantlist()));

            }
        });

        View footer = inflater.inflate(R.layout.footer, recyclerView, false);
        wrapAdapter.addFooter(footer);
    }

    @Override
    public void onLoadMore(RecyclerView recyclerView) {
        ((WantRecordsAdapter) ((WrapAdapter) recyclerView.getAdapter()).getWrapped()).loadMore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wantlist, container, false);
        initRecyclerView(view, inflater);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.activityOnDestroy();
    }
}
