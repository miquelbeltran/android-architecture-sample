//package work.beltran.discogsbrowser.api;
//
//import org.junit.Test;
//
//import rx.observers.TestSubscriber;
//import work.beltran.discogsbrowser.api.model.SearchResults;
//
///**
// * Created by Miquel Beltran on 8/27/16
// * More on http://beltran.work
// */
//public class SearchTest extends DiscogsServiceTest {
//
//    private static final String QUERY = "String";
//    private static final String TYPE = null;
//    private static final String FORMAT = null;
//    private static final String BARCODE = null;
//
//    @Test
//    public void testSearch() throws Exception {
//        TestSubscriber<SearchResults> subscriber = TestSubscriber.create();
//        service.search(QUERY, TYPE, FORMAT, BARCODE).subscribe(subscriber);
//        subscriber.assertNoErrors();
//        subscriber.assertCompleted();
//    }
//}
