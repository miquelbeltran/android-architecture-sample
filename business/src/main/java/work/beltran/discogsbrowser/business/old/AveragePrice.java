package work.beltran.discogsbrowser.business.old;

import android.util.Log;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.observables.MathObservable;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.MarketResult;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.currency.FixerService;
import work.beltran.discogsbrowser.currency.model.Rates;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class AveragePrice {
    private static final String TAG = AveragePrice.class.getCanonicalName();
    private DiscogsService service;
    private FixerService fixerService;
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;

    public AveragePrice(DiscogsService service, FixerService fixerService, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        this.service = service;
        this.fixerService = fixerService;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    /**
     * This function returns the first result only converted to the local currency.
     *
     * By removing the .first(), it will actually do the average.
     *
     * I want to make that configurable in the future.
     *
     * @param record
     * @param currency
     * @param type
     * @return
     */
    public Observable<Double> getAveragePrice(Record record, final String currency, String type) {
        Observable<MarketResult> marketResultObservable = service.getMarketResults(record.getInstance_id())
               .subscribeOn(subscribeOnScheduler)
               .observeOn(observeOnScheduler)
               .flatMap(new Func1<List<MarketResult>, Observable<MarketResult>>() {
                   @Override
                   public Observable<MarketResult> call(List<MarketResult> marketResults) {
                       Log.d(TAG, "Market SearchResults: " + marketResults.size());
                       return Observable.from(marketResults);
                   }
               });

        if (type.equals("0")) {
            marketResultObservable = marketResultObservable.first();
        }

       return MathObservable.averageDouble(
               marketResultObservable
               .flatMap(new Func1<MarketResult, Observable<Double>>() {
                   @Override
                   public Observable<Double> call(final MarketResult marketResult) {
                       return fixerService
                               .getRates(marketResult.getCurrency() + "," + currency)
                               .subscribeOn(subscribeOnScheduler)
                               .observeOn(observeOnScheduler)
                               .flatMap(new Func1<Rates, Observable<Double>>() {
                           @Override
                           public Observable<Double> call(Rates rates) {
                               String sPrice = marketResult.getPrice();
                               Matcher matcher = Pattern.compile("\\d+\\.\\d+").matcher(sPrice);
                               matcher.find();
                               double price = Double.valueOf(matcher.group());
                               if (rates.rates != null) {
                                   if (rates.rates.containsKey(marketResult.getCurrency())) {
                                       price /= rates.rates.get(marketResult.getCurrency());
                                   }
                               }
                               Log.d(TAG, sPrice + ": " + price);
                               return Observable.just(price);
                           }
                       });
                   }
               }));
    }
}
