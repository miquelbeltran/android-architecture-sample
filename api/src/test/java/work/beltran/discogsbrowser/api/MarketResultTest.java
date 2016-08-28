package work.beltran.discogsbrowser.api;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.api.model.MarketResult;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class MarketResultTest extends DiscogsServiceTest {

    private static final int ID = 6704805;

    @Ignore // Market search seems to be broken now
    @Test
    public void testGetMarketResults() throws Exception {
        TestSubscriber<List<MarketResult>> subscriber = TestSubscriber.create();
        service.getMarketResults(ID).subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }
}
