package work.beltran.discogsbrowser.ui.main.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import work.beltran.discogsbrowser.ui.main.CustomToolbar;
import work.beltran.discogsbrowser.ui.main.common.CircleTransform;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;

public class CollectionFragment extends Fragment implements LoadMoreOnScrollListener.Listener {
    private CollectionRecordsAdapter adapter;
    private ApiFrontend collection;
    private Picasso picasso;

    public CollectionFragment() {
        // Required empty public constructor
    }

    @Inject
    public void setAdapter(CollectionRecordsAdapter adapter) {
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

    public static CollectionFragment newInstance() {
        CollectionFragment fragment = new CollectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getApiComponent().inject(this);
        ((App) getActivity().getApplication()).getApiComponent().inject(adapter);
    }

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
            recyclerView.addOnScrollListener(new PicassoOnScrollListener(adapter));
            recyclerView.setLayoutManager(layoutManager);
            WrapAdapter wrapAdapter = new WrapAdapter(adapter);
            recyclerView.setAdapter(wrapAdapter);
            initHeaderFooter(inflater, recyclerView, wrapAdapter);
        }
    }

    private void initHeaderFooter(LayoutInflater inflater, RecyclerView recyclerView, WrapAdapter wrapAdapter) {
        final View header = inflater.inflate(R.layout.header, recyclerView, false);
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
                ((TextView)header.findViewById(R.id.textWantlist)).setText(userProfile.getUsername());
                ImageView imageView = (ImageView) header.findViewById(R.id.imageAvatar);
                picasso.load(userProfile.getAvatar_url())
                        .placeholder(R.drawable.ic_account_circle_black_48px)
                        .fit()
                        .centerCrop()
                        .transform(new CircleTransform())
                        .into(imageView);
                TextView inCollection = (TextView) header.findViewById(R.id.textInCollection);
                inCollection.setText(getResources().getString(R.string.in_collection, userProfile.getNum_collection()));

            }
        });
        CustomToolbar.setToolbar(this, header);
        View footer = inflater.inflate(R.layout.footer, recyclerView, false);
        wrapAdapter.addFooter(footer);
    }

    @Override
    public void onLoadMore(RecyclerView recyclerView) {
        ((RecordsAdapter) ((WrapAdapter) recyclerView.getAdapter()).getWrapped()).loadMore();
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
