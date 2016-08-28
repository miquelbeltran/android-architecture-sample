package work.beltran.discogsbrowser.app.login;

import android.content.Intent;

import work.beltran.discogsbrowser.app.base.BaseView;

/**
 * Created by Miquel Beltran on 15.05.16.
 * More on http://beltran.work
 */
public interface LoginView extends BaseView {

    void startActivity(Intent intent);

    void startLauncher();
}
