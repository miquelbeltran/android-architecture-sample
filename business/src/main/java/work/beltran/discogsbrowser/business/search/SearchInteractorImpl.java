package work.beltran.discogsbrowser.business.search;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import work.beltran.discogsbrowser.api.DiscogsService;
import work.beltran.discogsbrowser.api.model.SearchResults;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.business.RxJavaSchedulers;
import work.beltran.discogsbrowser.business.SearchInteractor;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class SearchInteractorImpl implements SearchInteractor {
    private DiscogsService service;
    private RxJavaSchedulers schedulers;

    public SearchInteractorImpl(DiscogsService service, RxJavaSchedulers schedulers) {
        this.service = service;
        this.schedulers = schedulers;
    }

    @Override
    public Observable<List<Record>> search(String query) {
        return  service.search(query, "release", null, null)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .map(new Func1<SearchResults, List<Record>>() {
                    @Override
                    public List<Record> call(SearchResults searchResults) {
                        return searchResults.getRecords();
                    }
                });
    }
}
