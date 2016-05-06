package work.beltran.discogsbrowser.api;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.api.model.MarketResult;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.api.network.AveragePrice;
import work.beltran.discogsbrowser.api.network.DiscogsService;
import work.beltran.discogsbrowser.currency.FixerService;
import work.beltran.discogsbrowser.currency.Rates;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricTestRunner.class)
public class AveragePriceTest {
    AveragePrice averagePrice;
    DiscogsService service;
    FixerService fixer;
    Record record;
    List<MarketResult> marketResultList;

    @Before
    public void setUp() throws Exception {
        record = new Record();
        record.setInstance_id(84825);
        service = mock(DiscogsService.class);
        fixer = mock(FixerService.class);
        marketResultList = new ArrayList<>();
        addMarketResult("GBP","\u00a385.00");
        addMarketResult("EUR","\u20ac389.00");
        addMarketResult("GBP","\u00a3120.00");
        addMarketResult("GBP","\u00a3160.00");
        addMarketResult("GBP","\u00a3160.00");
        addMarketResult("EUR","\u20ac175.00");
        addMarketResult("USD","$249.99");
        Observable<List<MarketResult>> result = Observable.just(marketResultList);
        when(service.getMarketResults(anyInt())).thenReturn(result);
        when(fixer.getRates(anyString())).thenReturn(Observable.just(new Rates()));
        averagePrice = new AveragePrice(service, fixer, Schedulers.immediate(), Schedulers.immediate());

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
        averagePrice.getAveragePrice(record, NumberFormat.getCurrencyInstance().getCurrency().getCurrencyCode(), 0).subscribe(subscriber);
        subscriber.assertNoErrors();
        Double price = subscriber.getOnNextEvents().get(0);
        assertThat(price).isEqualTo(85.0);
    }
}
