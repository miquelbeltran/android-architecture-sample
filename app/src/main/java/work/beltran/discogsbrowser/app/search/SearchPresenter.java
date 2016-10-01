package work.beltran.discogsbrowser.app.search;

import java.util.List;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.app.base.BasePresenter;
import work.beltran.discogsbrowser.app.common.RecordViewModel;
import work.beltran.discogsbrowser.business.SearchInteractor;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
public class SearchPresenter extends BasePresenter<SearchView> {

    SearchInteractor interactor;

    public SearchPresenter(SearchInteractor interactor) {
        this.interactor = interactor;
    }

    public void search(String query) {
        if (getView() != null) {
            getView().setLoading(true);
            getView().clear();
            unsubscribeAll();
        }
        addSubscription(
                interactor
                        .search(query)
                        .subscribe(new Observer<List<Record>>() {
                            @Override
                            public void onCompleted() {
                                if (getView() != null)
                                    getView().setLoading(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                if (getView() != null)
                                    getView().setLoading(false);
                            }

                            @Override
                            public void onNext(List<Record> records) {
                                if (getView() != null) {
                                    getView().display(RecordViewModel.createRecordsList(records));
                                }
                            }
                        }));
    }

}
