package work.beltran.discogsbrowser.ui.main.collection;

import android.util.Log;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import javax.inject.Inject;

import rx.Observer;
import work.beltran.discogsbrowser.api.network.AveragePrice;
import work.beltran.discogsbrowser.api.network.RecordsSubject;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class CollectionRecordsAdapter extends RecordsAdapter {
    private static final String TAG = CollectionRecordsAdapter.class.getCanonicalName();
    AveragePrice averagePrice;
    private Settings settings;

    public CollectionRecordsAdapter(RecordsSubject subject, Picasso picasso) {
        super(subject, picasso);
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
    protected void onBindViewHolder(final RecordViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        boolean showPrices = settings.getSharedPreferences().getBoolean("collection_prices", false);
        if (showPrices) {
            String type = settings.getSharedPreferences().getString("collection_prices_type", "0");
            averagePrice.getAveragePrice(recordList.get(position), "EUR", type)
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
                            holder.getBinding().textPrice.setText(format.format(aDouble));
                        }
                    });
        }
    }
}
