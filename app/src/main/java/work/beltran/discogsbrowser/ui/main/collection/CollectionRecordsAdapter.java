package work.beltran.discogsbrowser.ui.main.collection;

import work.beltran.discogsbrowser.business.base.RecordsApi;
import work.beltran.discogsbrowser.ui.main.common.UserRecordsAdapter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class CollectionRecordsAdapter extends UserRecordsAdapter {
    private static final String TAG = CollectionRecordsAdapter.class.getCanonicalName();

    public CollectionRecordsAdapter(RecordsApi subject) {
        super(subject);
    }

    @Override
    protected boolean getPreferencePricesDefault() {
        return true;
    }

    @Override
    protected String getPreferencePricesType() {
        return Settings.COLLECTION_PRICES_TYPE;
    }

    @Override
    protected String getPreferencePrices() {
        return Settings.COLLECTION_PRICES;
    }
}
