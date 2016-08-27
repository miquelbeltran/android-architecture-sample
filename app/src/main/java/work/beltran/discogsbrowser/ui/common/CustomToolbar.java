package work.beltran.discogsbrowser.ui.common;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.about.AboutActivity;
import work.beltran.discogsbrowser.ui.settings.SettingsActivity;

/**
 * Created by Miquel Beltran on 10.05.16.
 * More on http://beltran.work
 */
public class CustomToolbar {
    public static void setToolbar(final Fragment fragment, View header) {
        Toolbar toolbar = (Toolbar)header.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    Intent intent = new Intent(fragment.getActivity(), SettingsActivity.class);
                    fragment.startActivity(intent);
                    return true;
                }
                if (item.getItemId() == R.id.action_about) {
                    Intent intent = new Intent(fragment.getActivity(), AboutActivity.class);
                    fragment.startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}
