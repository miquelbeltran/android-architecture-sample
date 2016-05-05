package work.beltran.discogsbrowser.ui.main.wantlist;

import android.util.Log;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import rx.Observer;
import work.beltran.discogsbrowser.api.network.AveragePrice;
import work.beltran.discogsbrowser.api.network.RecordsSubject;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class WantRecordsAdapter extends RecordsAdapter {
    private static final String TAG = WantRecordsAdapter.class.getCanonicalName();
    AveragePrice averagePrice;

    public WantRecordsAdapter(RecordsSubject subject, Picasso picasso) {
        super(subject, picasso);
    }

    @Inject
    public void setAveragePrice(AveragePrice averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    protected void onBindViewHolder(final RecordViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        averagePrice.getAveragePrice(recordList.get(position))
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
                        holder.getBinding().textPrice.setText("$3.50");
                    }
                });
    }
}
