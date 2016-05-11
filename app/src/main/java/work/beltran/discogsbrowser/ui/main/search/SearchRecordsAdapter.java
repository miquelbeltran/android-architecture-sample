package work.beltran.discogsbrowser.ui.main.search;

import com.squareup.picasso.Picasso;

import work.beltran.discogsbrowser.api.network.SearchSubject;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;
import work.beltran.discogsbrowser.ui.settings.Settings;

/**
 * Created by Miquel Beltran on 06.05.16.
 * More on http://beltran.work
 */
public class SearchRecordsAdapter extends RecordsAdapter {
    public SearchRecordsAdapter(SearchSubject subject, Picasso picasso) {
        super(subject, picasso);
        subscription.unsubscribe();
    }

    @Override
    protected boolean getPreferencePricesDefault() {
        return false;
    }

    @Override
    protected String getPreferencePricesType() {
        return Settings.COLLECTION_PRICES_TYPE;
    }

    @Override
    protected String getPreferencePrices() {
        return Settings.COLLECTION_PRICES;
    }

    public void search(String query) {
        // remove all content
        recordList.clear();
        notifyDataSetChanged();
        // recreate subscription
        subscription.unsubscribe();
        subscribe();
        // show progressbar
        notifyDataSetChanged();
        ((SearchSubject) subject).search(query);
    }
}
