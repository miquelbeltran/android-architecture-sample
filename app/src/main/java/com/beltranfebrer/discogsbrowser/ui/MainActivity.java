package com.beltranfebrer.discogsbrowser.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beltranfebrer.discogsbrowser.App;
import com.beltranfebrer.discogsbrowser.R;
import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.extras.PicassoOnScrollListener;

public class MainActivity extends AppCompatActivity implements LoadMoreOnScrollListener.Listener {
    private RecordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.records_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RecordsAdapter();
        App.getComponent().inject(adapter);
        if (recyclerView != null) {
//            recyclerView.addOnScrollListener(new LoadMoreOnScrollListener(this));
            recyclerView.addOnScrollListener(new PicassoOnScrollListener(adapter));
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.activityOnDestroy();
    }

    @Override
    public void onLoadMore(RecyclerView recyclerView) {
        ((RecordsAdapter) recyclerView.getAdapter()).loadMore();
    }
}
