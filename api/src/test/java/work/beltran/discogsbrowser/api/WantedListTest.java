package work.beltran.discogsbrowser.api;

import org.junit.Test;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.api.model.UserWanted;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class WantedListTest extends DiscogsServiceTest {

    private static final String USER = "mike513";
    private static final int PAGE = 0;

    @Test
    public void testWantedList() throws Exception {
        TestSubscriber<UserWanted> subscriber = TestSubscriber.create();
        service.getWantedList(USER, PAGE).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }
}
