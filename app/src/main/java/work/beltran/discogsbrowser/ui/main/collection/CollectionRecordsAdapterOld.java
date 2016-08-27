package work.beltran.discogsbrowser.ui.main.collection;

import work.beltran.discogsbrowser.business.old.RecordsApi;
import work.beltran.discogsbrowser.ui.main.common.UserRecordsAdapterOld;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class CollectionRecordsAdapterOld extends UserRecordsAdapterOld {
    private static final String TAG = CollectionRecordsAdapterOld.class.getCanonicalName();

    public CollectionRecordsAdapterOld(RecordsApi subject) {
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
