package work.beltran.discogsbrowser.ui.main.wantlist;

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
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class WantRecordsAdapter extends RecordsAdapter {
    private static final String TAG = WantRecordsAdapter.class.getCanonicalName();
    AveragePrice averagePrice;
    Settings settings;

    public WantRecordsAdapter(RecordsSubject subject, Picasso picasso) {
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
        boolean showPrices = settings.getSharedPreferences().getBoolean("wantlist_prices", true);
        if (showPrices) {
            String type = settings.getSharedPreferences().getString("wantlist_prices_type", "0");
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
