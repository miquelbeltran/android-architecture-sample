package work.beltran.discogsbrowser.ui.collection;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.ApiFrontend;
import work.beltran.discogsbrowser.api.model.Record;
import work.beltran.discogsbrowser.databinding.CardRecordBinding;
import work.beltran.discogsbrowser.ui.errors.ErrorPresenter;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class RecordsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = RecordsAdapter.class.getCanonicalName();
    private ApiFrontend apiFrontend;
    private List<Record> recordList = new ArrayList<>();
    private Picasso picasso;
    private Subscription subscription;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private boolean showProgressBar = true;
    private ErrorPresenter errorPresenter;

    public RecordsAdapter(ApiFrontend apiFrontend, Picasso picasso) {
        this.picasso = picasso;
        this.apiFrontend = apiFrontend;
        subscribe();
    }

    @Inject
    public void setErrorPresenter(ErrorPresenter errorPresenter) {
        this.errorPresenter = errorPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_ITEM:
            default:
                CardRecordBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_record, parent, false);
                return new RecordViewHolder(binding);
            case VIEW_PROG:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
                return new ProgressBarViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecordViewHolder) {
            onBindViewHolder((RecordViewHolder) holder, position);
        }
    }

    private void onBindViewHolder(RecordViewHolder holder, int position) {
        holder.getBinding().setRecord(recordList.get(position));
        picasso.load(recordList.get(position).getBasicInformation().getThumb())
                .tag(this)
                .placeholder(R.drawable.music_record)
                .fit()
                .centerCrop()
                .into(holder.getBinding().recordThumb);
    }

    @Override
    public int getItemViewType(int position) {
        if (showProgressBar && position >= recordList.size())
            return VIEW_PROG;
        else
            return VIEW_ITEM;
    }

    @Override
    public int getItemCount() {
        return recordList.size() + (showProgressBar ? 1 : 0);
    }

    private void subscribe() {
        subscription = apiFrontend.getCollectionRecords().subscribe(new Observer<Record>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
                showProgressBar = false;
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
                notifyItemChanged(recordList.size() - 1);
            }
        });
    }

    public void activityOnDestroy() {
        subscription.unsubscribe();
    }

    public void loadMore() {
        Log.d(TAG, "Load More Requested");
        apiFrontend.loadMoreCollection();;
    }


    public class RecordViewHolder extends RecyclerView.ViewHolder {
        private CardRecordBinding binding;

        public RecordViewHolder(CardRecordBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public CardRecordBinding getBinding() {
            return binding;
        }
    }

    public class ProgressBarViewHolder extends RecyclerView.ViewHolder {

        public ProgressBarViewHolder(View view) {
            super(view);
        }
    }
}
