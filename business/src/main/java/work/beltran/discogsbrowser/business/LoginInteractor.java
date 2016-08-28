package work.beltran.discogsbrowser.business;

import android.net.Uri;

import rx.Observable;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public interface LoginInteractor {
    Observable<Uri> requestTokens(String apiConsumerKey, String apiConsumerSecret);

    Observable<Boolean> requisterAccessToken(Uri uri, String apiConsumerKey, String apiConsumerSecret);
}
