package work.beltran.discogsbrowser.api;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.api.model.MarketResult;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.api.network.AveragePrice;
import work.beltran.discogsbrowser.api.network.DiscogsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class AveragePriceTest {
    AveragePrice averagePrice;
    DiscogsService service;
    Record record;

    @Before
    public void setUp() throws Exception {
        record = new Record();
        record.setInstance_id(84825);
        service = mock(DiscogsService.class);
        MarketResult marketResult = new MarketResult();
        marketResult.setCurrency("GBP");
        marketResult.setPrice("\u00a385.00");
        Observable<MarketResult> result = Observable.just(marketResult);
        when(service.getMarketResults(anyInt())).thenReturn(result);
        averagePrice = new AveragePrice(service, Schedulers.immediate(), Schedulers.immediate());

    }


    @Test
    public void testAveragePrice() throws Exception {
        TestSubscriber<Double> subscriber = new TestSubscriber<>();
        averagePrice.getAveragePrice(record).subscribe(subscriber);
        Double price = subscriber.getOnNextEvents().get(0);
        assertThat(price).isEqualTo(3.50);
    }
}
