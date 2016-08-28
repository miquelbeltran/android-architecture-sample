package work.beltran.discogsbrowser.app.errors;

import android.util.Log;

import work.beltran.discogsbrowser.business.settings.Settings;

/**
 * Created by Miquel Beltran on 03.05.16.
 * More on http://beltran.work
 */
public class ErrorPresenter {
    private static final String TAG = ErrorPresenter.class.getCanonicalName();
    private ErrorHandlingView view;
    private Settings settings;

    public ErrorPresenter(Settings settings) {
        this.settings = settings;
    }

    public void onError(Throwable e) {
        Log.e(TAG, "onError() " + e.getMessage());
        e.printStackTrace();
        settings.storeApiKey("");
        if (view == null) {
            Log.e(TAG, "ErrorHandlingView not set!");
        } else {
            view.showErrorInfo(e.getMessage());
        }
    }

    public void setView(ErrorHandlingView view) {
        this.view = view;
    }
}
