package work.beltran.discogsbrowser.api;

import org.junit.Test;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.api.model.UserCollection;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class UserCollectionTest extends DiscogsServiceTest {

    private static final int PAGE = 0;

    @Test
    public void testListCollection() throws Exception {
        TestSubscriber<UserCollection> subscriber = TestSubscriber.create();
        service.listRecords(USER, PAGE).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }
}
