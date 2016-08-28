package work.beltran.discogsbrowser.business;

import rx.Observable;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserProfile;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public interface ProfileInteractor {
    Observable<UserIdentity> getIdentity();
    Observable<UserProfile> getProfile();
}
