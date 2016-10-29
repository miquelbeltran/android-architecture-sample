package work.beltran.discogsbrowser.api;

import org.junit.Test;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.api.model.UserCollection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
public class GetRecordFromCollectionTest extends DiscogsServiceTest {
    private static final int RECORD_ID = 6095128;
    private static final int RECORD_ID_NOT_FOUND = 1;

    @Test
    public void testGetRecordFromCollection() throws Exception {
        TestSubscriber<UserCollection> testSubscriber = TestSubscriber.create();
        service.getRecordInCollection(USER, RECORD_ID).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        UserCollection userCollection = testSubscriber.getOnNextEvents().get(0);
        assertThat(userCollection.getRecords().size()).isEqualTo(1);
    }

    @Test
    public void testGetRecordNotFound() throws Exception {
        TestSubscriber<UserCollection> testSubscriber = TestSubscriber.create();
        service.getRecordInCollection(USER, RECORD_ID_NOT_FOUND).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        UserCollection userCollection = testSubscriber.getOnNextEvents().get(0);
        assertThat(userCollection.getRecords().size()).isEqualTo(0);
    }
}
