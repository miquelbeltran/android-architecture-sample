package work.beltran.discogsbrowser.ui.collection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.extras.PicassoOnScrollListener;

import javax.inject.Inject;

import work.beltran.discogsbrowser.ui.App;
import work.beltran.discogsbrowser.R;

public class CollectionActivity extends AppCompatActivity implements LoadMoreOnScrollListener.Listener {
    private RecordsAdapter adapter;

    @Inject
    public void setAdapter(RecordsAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.records_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new LoadMoreOnScrollListener(this));
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
