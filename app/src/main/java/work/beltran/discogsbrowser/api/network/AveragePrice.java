package work.beltran.discogsbrowser.api.network;

import rx.Observable;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class AveragePrice {
   public Observable<Double> getAveragePrice(Record record) {
      return Observable.just(3.50);
   }
}
