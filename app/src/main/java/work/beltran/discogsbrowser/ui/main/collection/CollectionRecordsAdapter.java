package work.beltran.discogsbrowser.ui.main.collection;

import com.squareup.picasso.Picasso;

import work.beltran.discogsbrowser.api.network.RecordsSubject;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;

/**
 * Created by Miquel Beltran on 05.05.16.
 * More on http://beltran.work
 */
public class CollectionRecordsAdapter extends RecordsAdapter {
    public CollectionRecordsAdapter(RecordsSubject subject, Picasso picasso) {
        super(subject, picasso);
    }
}
