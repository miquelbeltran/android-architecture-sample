package work.beltran.discogsbrowser.ui.main.wantlist;

import work.beltran.discogsbrowser.business.old.RecordsApi;
import work.beltran.discogsbrowser.ui.common.UserRecordsAdapterOld;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class WantRecordsAdapterOld extends UserRecordsAdapterOld {
    private static final String TAG = WantRecordsAdapterOld.class.getCanonicalName();

    public WantRecordsAdapterOld(RecordsApi subject) {
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
