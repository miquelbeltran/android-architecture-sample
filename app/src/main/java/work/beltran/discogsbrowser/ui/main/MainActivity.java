package work.beltran.discogsbrowser.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.List;

import javax.inject.Inject;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.App;
import work.beltran.discogsbrowser.ui.errors.ErrorHandlingView;
import work.beltran.discogsbrowser.ui.errors.ErrorPresenter;
import work.beltran.discogsbrowser.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements ErrorHandlingView {
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Inject
    public NavigationAdapter navigationAdapter;

    @Inject
    public ErrorPresenter errorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getAppComponent().inject(this);
        errorPresenter.setView(this);

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
                navigationAdapter.tabItem(position, wasSelected);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
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
