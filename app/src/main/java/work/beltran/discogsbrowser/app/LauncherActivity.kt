package work.beltran.discogsbrowser.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import javax.inject.Inject

import work.beltran.discogsbrowser.app.login.LoginActivity
import work.beltran.discogsbrowser.app.main.MainActivity
import work.beltran.discogsbrowser.app.settings.Settings

class LauncherActivity : Activity() {

    @Inject
    lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        if (settings.userToken.isEmpty() || settings.userSecret.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            (application as App).initApiComponent(settings.userToken, settings.userSecret)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Workaround for:
     * http://stackoverflow.com/questions/32169303/activity-did-not-call-finish-api-23
     */
    override fun onStart() {
        super.onStart()
        setVisible(true)
    }
}
