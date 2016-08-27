package work.beltran.discogsbrowser.ui.main.search;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.business.old.SearchSubject;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapterOld;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 06.05.16.
 * More on http://beltran.work
 */
public class SearchRecordsAdapterOld extends RecordsAdapterOld {
    private static final String TAG = SearchRecordsAdapterOld.class.getCanonicalName();
    private final SearchSubject subject;

    public SearchRecordsAdapterOld(SearchSubject subject)  {
        this.subject = subject;
    }

    protected void subscribe(Observable<Record> observable) {
        subscription = observable
                .subscribe(new Observer<Record>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted()");
                        notifyItemRemoved(recordList.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError() " + e.getMessage());
                        errorPresenter.onError(e);
                    }

                    @Override
                    public void onNext(Record record) {
                        Log.d(TAG, "onNext(" + record.getInstance_id() + ")");
                        recordList.add(record);
                        notifyItemInserted(recordList.size() - 1);
                    }
                });
    }


    protected boolean getPreferencePricesDefault() {
        return true;
    }

    protected String getPreferencePricesType() {
        return Settings.SEARCH_PRICES_TYPE;
    }

    protected String getPreferencePrices() {
        return Settings.SEARCH_PRICES;
    }

    public void search(String query) {
        // remove all content
        recordList.clear();
        notifyDataSetChanged();
        // recreate subscription
        if (subscription != null)
            subscription.unsubscribe();
        subscribe(subject.search(query, 1));
        // show progressbar
        notifyDataSetChanged();
    }
}
