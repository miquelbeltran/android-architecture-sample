package work.beltran.discogsbrowser.ui.main.common;

import android.os.Bundle;
import android.util.Log;

import rx.Observer;
import work.beltran.discogsbrowser.api.model.RecordsWithPagination;
import work.beltran.discogsbrowser.api.model.pagination.Pagination;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.business.old.RecordsApi;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public abstract class UserRecordsAdapter extends RecordsAdapter {
    private static final String TAG = UserRecordsAdapter.class.getCanonicalName();
    private static final String PAGINATION = "PAGINATION";
    protected RecordsApi subject;
    private Pagination pagination;

    public UserRecordsAdapter(RecordsApi subject) {
        this.subject = subject;
        subscribe();
    }

    @Override
    public Bundle getBundle() {
        Bundle bundle = super.getBundle();
        //bundle.putParcelable(PAGINATION, pagination);
        return bundle;
    }

    @Override
    public void loadBundle(Bundle bundle) {
        super.loadBundle(bundle);
        //pagination = bundle.getParcelable(PAGINATION);
    }

    protected void subscribe() {
        if (subscription == null || subscription.isUnsubscribed()) {
            int page = pagination != null ? pagination.getPage() + 1 : 1;
            subscription = subject
                    .getRecordsFromService(page)
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
                            pagination = recordsWithPagination.getPagination();
                            for (Record record : recordsWithPagination.getRecords()) {
                                Log.d(TAG, "onNext(" + record.getInstance_id() + ")");
                                recordList.add(record);
                            }
                            notifyItemRangeInserted(range, recordsWithPagination.getRecords().size());
                        }
                    });
        }
    }

    public void loadMore() {
        if (pagination == null) {
            return;
        }
        if (pagination.getPage() < pagination.getPages()) {
            Log.d(TAG, "Load More Requested");
            subscribe();
        }
    }
}
