package work.beltran.discogsbrowser.ui.main.wantlist;

import work.beltran.discogsbrowser.business.RecordsApi;
import work.beltran.discogsbrowser.ui.main.common.UserRecordsAdapter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class WantRecordsAdapter extends UserRecordsAdapter {
    private static final String TAG = WantRecordsAdapter.class.getCanonicalName();

    public WantRecordsAdapter(RecordsApi subject) {
        super(subject);
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
