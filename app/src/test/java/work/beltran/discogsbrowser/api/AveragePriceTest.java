package work.beltran.discogsbrowser.api;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.api.network.AveragePrice;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class AveragePriceTest {
    AveragePrice averagePrice;
    Record record;

    @Before
    public void setUp() throws Exception {
        record = new Record();
        record.setInstance_id(84825);
        averagePrice = new AveragePrice();
    }

    @Test
    public void testAveragePrice() throws Exception {
        TestSubscriber<Double> subscriber = new TestSubscriber<>();
        averagePrice.getAveragePrice(record).subscribe(subscriber);
        Double price = subscriber.getOnNextEvents().get(0);
        assertThat(price).isEqualTo(3.50);
    }
}
