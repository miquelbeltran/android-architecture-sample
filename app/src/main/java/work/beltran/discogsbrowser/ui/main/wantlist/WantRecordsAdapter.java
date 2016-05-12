package work.beltran.discogsbrowser.ui.main.wantlist;

import com.squareup.picasso.Picasso;

import work.beltran.discogsbrowser.api.network.RecordsApi;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class WantRecordsAdapter extends RecordsAdapter {
    private static final String TAG = WantRecordsAdapter.class.getCanonicalName();

    public WantRecordsAdapter(RecordsApi subject, Picasso picasso) {
        super(subject, picasso);
    }

    @Override
    protected boolean getPreferencePricesDefault() {
        return true;
    }

    @Override
    protected String getPreferencePricesType() {
        return Settings.WANTLIST_PRICES_TYPE;
    }

    @Override
    protected String getPreferencePrices() {
        return Settings.WANTLIST_PRICES;
    }
}
