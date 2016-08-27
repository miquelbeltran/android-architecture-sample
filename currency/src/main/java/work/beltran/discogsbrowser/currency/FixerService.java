package work.beltran.discogsbrowser.currency;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public interface FixerService {

//    http://api.fixer.io/latest?symbols=USD,GBP
//
//    {
//      "base": "EUR",
//      "date": "2016-05-05",
//      "rates": {
//        "GBP": 0.7886,
//        "USD": 1.1439
//      }
//    }
    @GET("latest")
    rx.Observable<Rates> getRates(@Query("symbols") String symbols);
}
