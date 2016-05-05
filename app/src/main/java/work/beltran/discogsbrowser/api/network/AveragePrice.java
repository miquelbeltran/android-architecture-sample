package work.beltran.discogsbrowser.api.network;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.observables.MathObservable;
import work.beltran.discogsbrowser.api.model.MarketResult;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class AveragePrice {
    private DiscogsService service;
    private Scheduler subscribeOnScheduler;
    private Scheduler observeOnScheduler;

    public AveragePrice(DiscogsService service, Scheduler subscribeOnScheduler, Scheduler observeOnScheduler) {
        this.service = service;
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    public Observable<Double> getAveragePrice(Record record) {
       return MathObservable.averageDouble(service.getMarketResults(record.getInstance_id())
               .subscribeOn(subscribeOnScheduler)
               .observeOn(observeOnScheduler)
               .flatMap(new Func1<MarketResult, Observable<Double>>() {
                   @Override
                   public Observable<Double> call(MarketResult marketResult) {
                       return Observable.just(3.50);
                   }
               })
               .filter(new Func1<Double, Boolean>() {
                   @Override
                   public Boolean call(Double aDouble) {
                       return aDouble > 0;
                   }
               }));
    }
}
