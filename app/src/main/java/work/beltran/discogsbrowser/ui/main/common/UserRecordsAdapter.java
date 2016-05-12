package work.beltran.discogsbrowser.ui.main.common;

import android.util.Log;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.RecordsWithPagination;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.api.network.RecordsApi;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public abstract class UserRecordsAdapter extends RecordsAdapter {
    private static final String TAG = UserRecordsAdapter.class.getCanonicalName();
    protected RecordsApi subject;

    public UserRecordsAdapter(RecordsApi subject) {
        this.subject = subject;
        subscribe();
    }


    protected void subscribe() {
        subscription = subject
                .getRecordsFromService(1)
                .subscribe(new Observer<RecordsWithPagination>() {
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
                    public void onNext(RecordsWithPagination recordsWithPagination) {
                        int range = recordList.size();
                        for(Record record : recordsWithPagination.getRecords()) {
                            Log.d(TAG, "onNext(" + record.getInstance_id() + ")");
                            recordList.add(record);
                        }
                        notifyItemRangeInserted(range, recordsWithPagination.getRecords().size());
                    }
                });
    }

    public void loadMore() {
        Log.d(TAG, "Load More Requested");
//        subject.loadMoreData();
    }




}
