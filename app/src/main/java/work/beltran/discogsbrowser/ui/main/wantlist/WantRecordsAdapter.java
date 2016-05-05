package work.beltran.discogsbrowser.ui.main.wantlist;

import com.squareup.picasso.Picasso;

import work.beltran.discogsbrowser.api.network.RecordsSubject;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class WantRecordsAdapter extends RecordsAdapter {
    public WantRecordsAdapter(RecordsSubject subject, Picasso picasso) {
        super(subject, picasso);
    }
}
