package work.beltran.discogsbrowser.ui.main.common;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.business.base.AveragePrice;
import work.beltran.discogsbrowser.ui.errors.ErrorPresenter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 12.05.16.
 * More on http://beltran.work
 */
public abstract class RecordsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = RecordsAdapter.class.getCanonicalName();
    protected List<Record> recordList = new ArrayList<>();
    private Picasso picasso;
    protected ErrorPresenter errorPresenter;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private Settings settings;
    private AveragePrice averagePrice;
    protected Subscription subscription;

    @Inject
    public void setPicasso(Picasso picasso) {
        this.picasso = picasso;
    }

    @Inject
    public void setErrorPresenter(ErrorPresenter errorPresenter) {
        this.errorPresenter = errorPresenter;
    }

    @Inject
    public void setAveragePrice(AveragePrice averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Inject
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_ITEM:
            default:
//                CardRecordBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_record, parent, false);
//                return new RecordViewHolder(binding);
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

    protected void onBindViewHolder(final RecordViewHolder holder, int position) {
//        holder.getBinding().setRecord(recordList.get(position));
        if (!recordList.get(position).getBasicInformation().getThumb().isEmpty()) {
//            picasso.load(recordList.get(position).getBasicInformation().getThumb())
//                    .tag(this)
//                    .placeholder(R.drawable.music_record)
//                    .fit()
//                    .centerCrop()
//                    .into(holder.getBinding().recordThumb);
        }
        boolean showPrices = settings.getSharedPreferences().getBoolean(getPreferencePrices(), getPreferencePricesDefault());
        if (showPrices) {
            String type = settings.getSharedPreferences().getString(getPreferencePricesType(), "0");
            Subscription subscription = averagePrice.getAveragePrice(recordList.get(position),
                    NumberFormat.getCurrencyInstance().getCurrency().getCurrencyCode(),
                    type)
                    .subscribe(new Observer<Double>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: " + e.getMessage());
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(Double aDouble) {
                            NumberFormat format = NumberFormat.getCurrencyInstance();
//                            holder.getBinding().textPrice.setText(format.format(aDouble));
                        }
                    });
            holder.setPriceSubscription(subscription);
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof RecordViewHolder) {
            onViewRecycled((RecordViewHolder) holder);
        }
    }

    private void onViewRecycled(RecordViewHolder holder) {
//        holder.getBinding().textPrice.setText("");
        Subscription subscription = holder.getPriceSubscription();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public Bundle getBundle() {
        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("records", (ArrayList<? extends Parcelable>) recordList);
        return bundle;
    }

    public void loadBundle(Bundle bundle) {
        if (bundle != null) {
            if (subscription != null)
                subscription.unsubscribe();
            recordList.clear();
//            List<Record> records = bundle.getParcelableArrayList("records");
//            for(Record record : records) {
//                recordList.add(record);
//            }
            notifyDataSetChanged();
        }
    }

    protected abstract boolean getPreferencePricesDefault();

    protected abstract String getPreferencePricesType();

    protected abstract String getPreferencePrices();

    @Override
    public int getItemViewType(int position) {
        if (showProgressbar() && position == recordList.size())
            return VIEW_PROG;
        else
            return VIEW_ITEM;
    }

    private boolean showProgressbar() {
        return subscription != null && !subscription.isUnsubscribed();
    }

    public void activityOnDestroy() {
        subscription.unsubscribe();
    }

    @Override
    public int getItemCount() {
        return recordList.size() + (showProgressbar() ? 1 : 0);
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {
        private Subscription priceSubscription;

        public RecordViewHolder(View view) {
            super(view);
        }

        public void setPriceSubscription(Subscription priceSubscription) {
            this.priceSubscription = priceSubscription;
        }

        public Subscription getPriceSubscription() {
            return priceSubscription;
        }
    }

    public class ProgressBarViewHolder extends RecyclerView.ViewHolder {

        public ProgressBarViewHolder(View view) {
            super(view);
        }
    }
}
