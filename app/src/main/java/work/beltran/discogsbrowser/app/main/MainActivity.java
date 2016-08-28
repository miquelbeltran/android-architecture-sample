package work.beltran.discogsbrowser.app.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.app.App;
import work.beltran.discogsbrowser.app.collection.CollectionView;
import work.beltran.discogsbrowser.app.di.ApiComponent;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Inject
    public NavigationAdapter navigationAdapter;

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_library_music_white_48px, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_favorite_white_48px, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_search_white_48px, R.color.colorPrimary);

        // Add items
        navigation.addItem(item1);
        navigation.addItem(item2);
        navigation.addItem(item3);

        initAdapter();
        pager.setAdapter(navigationAdapter);

        // Set listener
        navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                pager.setCurrentItem(position);
            }
        });
    }

    private void initAdapter() {
        CollectionView view = new CollectionView(this);
        ApiComponent apiComponent = ((App) getApplication()).getApiComponent();
        if (apiComponent != null)
            apiComponent.inject(view);
        navigationAdapter.setViews(Arrays.<View>asList(view));
    }
}
