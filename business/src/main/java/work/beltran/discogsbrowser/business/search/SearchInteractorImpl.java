package work.beltran.discogsbrowser.business.search;

import rx.Observable;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.business.SearchInteractor;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class SearchInteractorImpl implements SearchInteractor {
    @Override
    public Observable<Record> search(String query, int nextPage) {
        return null;
    }
}
