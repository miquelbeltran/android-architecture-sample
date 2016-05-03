package work.beltran.discogsbrowser.ui.collection;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.extras.PicassoOnScrollListener;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.App;
import work.beltran.discogsbrowser.ui.errors.ErrorHandlingView;
import work.beltran.discogsbrowser.ui.errors.ErrorPresenter;
import work.beltran.discogsbrowser.ui.login.LoginActivity;

public class CollectionActivity extends AppCompatActivity implements LoadMoreOnScrollListener.Listener, ErrorHandlingView {
    private RecordsAdapter adapter;
    private ErrorPresenter errorPresenter;

    @Inject
    public void setAdapter(RecordsAdapter adapter) {
        this.adapter = adapter;
    }

    @Inject
    public void setErrorPresenter(ErrorPresenter errorPresenter) {
        this.errorPresenter = errorPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getApiComponent().inject(this);
        errorPresenter.setView(this);
        adapter.setErrorPresenter(errorPresenter);
        initRecyclerView();

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_library_music_white_48px, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_favorite_white_48px, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_search_white_48px, R.color.colorPrimary);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
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

    @Override
    public void showErrorInfo(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_error)
                .setMessage(R.string.dialog_error_message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Intent intent = new Intent(CollectionActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}
