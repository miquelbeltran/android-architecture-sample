package work.beltran.discogsbrowser.api;

import org.junit.Test;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
public class AddAndRemoveToCollectionTest extends DiscogsServiceTest {

    static int RELEASE_ID = 4855808;

    @Test
    public void testAddAndDelete() throws Exception {
        // Add to collection
        TestSubscriber<Void> testSubscriber = TestSubscriber.create();
        service.addToCollection(USER, RELEASE_ID).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        TestSubscriber<UserCollection> userCollectionTestSubscriber = TestSubscriber.create();
        service.getRecordInCollection(USER, RELEASE_ID).subscribe(userCollectionTestSubscriber);
        userCollectionTestSubscriber.assertNoErrors();
        userCollectionTestSubscriber.assertCompleted();
        UserCollection userCollection = userCollectionTestSubscriber.getOnNextEvents().get(0);
        for(Record record: userCollection.getRecords()) {
            // Delete each instance from collection
            testSubscriber = TestSubscriber.create();
            service.removeFromCollection(USER, RELEASE_ID, record.getInstanceId())
                    .subscribe(testSubscriber);
            testSubscriber.assertNoErrors();
            testSubscriber.assertCompleted();
        }
    }
}
