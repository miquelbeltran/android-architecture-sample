package work.beltran.discogsbrowser.api;

import org.junit.Test;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.api.model.UserProfile;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class UserProfileTest extends DiscogsServiceTest {
    private static final String USER = "mike513";

    @Test
    public void testGetUserProfile() throws Exception {
        TestSubscriber<UserProfile> subscriber = TestSubscriber.create();
        service.getUserProfile(USER).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }
}
