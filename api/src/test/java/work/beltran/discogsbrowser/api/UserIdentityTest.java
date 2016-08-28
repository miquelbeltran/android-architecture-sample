package work.beltran.discogsbrowser.api;

import org.junit.Test;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.api.model.UserIdentity;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class UserIdentityTest extends DiscogsServiceTest {

    @Test
    public void testGetUserIdentity() throws Exception {
        TestSubscriber<UserIdentity> subscriber = TestSubscriber.create();
        service.getUserIdentity().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }
}
