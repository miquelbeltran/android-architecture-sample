package work.beltran.discogsbrowser.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.App;
import work.beltran.discogsbrowser.ui.errors.ErrorHandlingView;
import work.beltran.discogsbrowser.ui.errors.ErrorPresenter;
import work.beltran.discogsbrowser.ui.login.LoginActivity;
import work.beltran.discogsbrowser.ui.main.collection.CollectionFragment;
import work.beltran.discogsbrowser.ui.main.search.SearchFragment;
import work.beltran.discogsbrowser.ui.main.wantlist.WantlistFragment;

public class MainActivity extends AppCompatActivity implements ErrorHandlingView, NavigationView {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private ErrorPresenter errorPresenter;
    private NavigationPresenter navigationPresenter;
    private Map<FragmentTag, Fragment> fragmentMap;

    @Inject
    public void setErrorPresenter(ErrorPresenter errorPresenter) {
        this.errorPresenter = errorPresenter;
    }

    @Inject
    public void setNavigationPresenter(NavigationPresenter navigationPresenter) {
        this.navigationPresenter = navigationPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getAppComponent().inject(this);
        errorPresenter.setView(this);
        navigationPresenter.setView(this);

        createFragments();

        final AHBottomNavigation navigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_library_music_white_48px, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_favorite_white_48px, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_search_white_48px, R.color.colorPrimary);

        // Add items
        navigation.addItem(item1);
        navigation.addItem(item2);
        navigation.addItem(item3);

        // Set listener
        navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                navigationPresenter.tabItem(position, wasSelected);
            }
        });

        if (savedInstanceState == null) {
            showFragment(FragmentTag.Collection, true);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null ) {
            FragmentTag savedTag = FragmentTag.valueOf(savedInstanceState.getString("TAG"));
            showFragment(savedTag, true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment.isVisible()) {
                outState.putString("TAG", fragment.getTag());
                break;
            }
        }
    }

    private void createFragments() {
        fragmentMap = new HashMap<>();
        fragmentMap.put(FragmentTag.Collection, CollectionFragment.newInstance());
        fragmentMap.put(FragmentTag.Wantlist, WantlistFragment.newInstance());
        fragmentMap.put(FragmentTag.Search, new SearchFragment());
    }

    @Override
    public void showFragment(FragmentTag tag, boolean toRight) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag.name());
        List<Fragment> fragmentsToHide = getSupportFragmentManager().getFragments();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (toRight)
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        else
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        if (fragmentsToHide != null) {
            for (Fragment fr : fragmentsToHide) {
                ft.hide(fr);
            }
        }
        if (fragment == null) {
            fragment = fragmentMap.get(tag);
            ft.add(R.id.fragment_container, fragment, tag.name());
        } else {
            ft.show(fragment);
        }
        ft.commit();
        getSupportFragmentManager().executePendingTransactions();
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
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}
