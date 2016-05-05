package work.beltran.discogsbrowser.api;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    List<MarketResult> marketResultList;

    @Before
    public void setUp() throws Exception {
        record = new Record();
        record.setInstance_id(84825);
        service = mock(DiscogsService.class);
        marketResultList = new ArrayList<>();
        addMarketResult("GBP","\u00a385.00");
        addMarketResult("EUR","\u20ac389.00");
        addMarketResult("GBP","\u00a3120.00");
        addMarketResult("GBP","\u00a3160.00");
        addMarketResult("GBP","\u00a3160.00");
        addMarketResult("EUR","\u20ac175.00");
        addMarketResult("USD","$249.99");
        Observable<MarketResult> result = Observable.from(marketResultList);
        when(service.getMarketResults(anyInt())).thenReturn(result);
        averagePrice = new AveragePrice(service, Schedulers.immediate(), Schedulers.immediate());

    }

    @NonNull
    private void addMarketResult(String currency, String price) {
        MarketResult marketResult = new MarketResult();
        marketResult.setCurrency(currency);
        marketResult.setPrice(price);
        marketResultList.add(marketResult);
    }


    @Test
    public void testAveragePrice() throws Exception {
        TestSubscriber<Double> subscriber = new TestSubscriber<>();
        averagePrice.getAveragePrice(record).subscribe(subscriber);
        Double price = subscriber.getOnNextEvents().get(0);
        assertThat(price).isEqualTo(3.50);
    }
}
