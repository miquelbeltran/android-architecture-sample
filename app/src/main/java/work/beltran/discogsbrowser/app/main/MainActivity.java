package work.beltran.discogsbrowser.app.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.Arrays;
import java.util.Collections;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.app.App;
import work.beltran.discogsbrowser.app.collection.CollectionFrameLayout;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;
import work.beltran.discogsbrowser.app.di.ApiComponent;
import work.beltran.discogsbrowser.app.search.SearchFrameLayout;
import work.beltran.discogsbrowser.app.wantlist.WantlistFrameLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final String STATE_COLLECTION = "STATE_COLLECTION";
    private static final String STATE_WANTLIST = "STATE_WANTLIST";
    private static final String STATE_SEARCH = "STATE_SEARCH";
    public static final String INTENT_FILTER_ADD_COL = "work.beltran.discogsbrowser.addtocol";
    public static final String INTENT_FILTER_RECORD_ITEM_EXTRA = "INTENT_FILTER_RECORD_ITEM_EXTRA";
    public static final String INTENT_FILTER_REMOVE_COL = "work.beltran.discogsbrowser.removetocol";

    @Inject
    public NavigationAdapter navigationAdapter;

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation navigation;

    private CollectionFrameLayout collectionFrameLayout;
    private WantlistFrameLayout wantlistFrameLayout;
    private SearchFrameLayout searchFrameLayout;
    private BroadcastReceiver addedToColReceiver;
    private BroadcastReceiver removeFromColReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);
        initNavBar(savedInstanceState);
        registerReceivers();
    }

    private void initNavBar(Bundle savedInstanceState) {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_library_music_white_48px, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_favorite_white_48px, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_search_white_48px, R.color.colorPrimary);
        navigation.addItem(item1);
        navigation.addItem(item2);
        navigation.addItem(item3);
        initAdapter(savedInstanceState);
        navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                pager.setCurrentItem(position);
            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigation.setCurrentItem(position, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initAdapter(Bundle savedInstanceState) {
        collectionFrameLayout = new CollectionFrameLayout(this, R.id.CollectionView);
        ApiComponent apiComponent = ((App) getApplication()).getApiComponent();
        if (apiComponent != null)
            apiComponent.inject(collectionFrameLayout);
        if (savedInstanceState != null) {
            Parcelable state = savedInstanceState.getParcelable(STATE_COLLECTION);
            if (state != null) {
                collectionFrameLayout.onRestoreInstanceState(state);
            }
        }
        wantlistFrameLayout = new WantlistFrameLayout(this, R.id.WantlistView);
        if (apiComponent != null)
            apiComponent.inject(wantlistFrameLayout);
        if (savedInstanceState != null) {
            Parcelable state = savedInstanceState.getParcelable(STATE_WANTLIST);
            if (state != null) {
                wantlistFrameLayout.onRestoreInstanceState(state);
            }
        }
        searchFrameLayout = new SearchFrameLayout(this, R.id.SearchView);
        if (apiComponent != null)
            apiComponent.inject(searchFrameLayout);
        if (savedInstanceState != null) {
            Parcelable state = savedInstanceState.getParcelable(STATE_SEARCH);
            if (state != null) {
                searchFrameLayout.onRestoreInstanceState(state);
            }
        }
        navigationAdapter.setViews(
                Arrays.<View>asList(
                        collectionFrameLayout,
                        wantlistFrameLayout,
                        searchFrameLayout));
        pager.setAdapter(navigationAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(STATE_COLLECTION, collectionFrameLayout.onSaveInstanceState());
        outState.putParcelable(STATE_WANTLIST, wantlistFrameLayout.onSaveInstanceState());
        outState.putParcelable(STATE_SEARCH, searchFrameLayout.onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    private void registerReceivers() {
        IntentFilter intentFilter = new IntentFilter(INTENT_FILTER_ADD_COL);
        addedToColReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                RecordAdapterItem item = intent.getParcelableExtra(INTENT_FILTER_RECORD_ITEM_EXTRA);
                collectionFrameLayout.addRecords(Collections.singletonList(item));
            }
        };
        registerReceiver(addedToColReceiver, intentFilter);
        intentFilter = new IntentFilter(INTENT_FILTER_REMOVE_COL);
        removeFromColReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                RecordAdapterItem item = intent.getParcelableExtra(INTENT_FILTER_RECORD_ITEM_EXTRA);
                collectionFrameLayout.removeRecord(item.getReleaseId());
            }
        };
        registerReceiver(removeFromColReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(addedToColReceiver);
        unregisterReceiver(removeFromColReceiver);
        super.onDestroy();
    }
}
