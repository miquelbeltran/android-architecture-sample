package work.beltran.discogsbrowser.app.wantlist;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.UserProfile;

/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
class WantlistHeader {
    private static final String STATE_WANTLIST = "STATE_WANTLIST";
    private static final String STATE_WANTLIST_COUNT = "STATE_WANTLIST_COUNT";
    @BindView(R.id.text_wantlist)
    TextView textWantlist;
    @BindView(R.id.text_wantlist_count)
    TextView textWantlistcount;

    void bind(UserProfile userProfile, Context context) {
        textWantlist.setText(context.getResources().getString(
                        R.string.user_wantlist,
                        userProfile.getUsername()));
        textWantlistcount.setText(context.getResources().getString(
                        R.string.in_wantlist,
                        userProfile.getNumWantlist()));
    }

    Bundle getState() {
        Bundle bundle = new Bundle();
        bundle.putString(STATE_WANTLIST, textWantlist.getText().toString());
        bundle.putString(STATE_WANTLIST_COUNT, textWantlistcount.getText().toString());
        return bundle;
    }

    void loadState(Bundle bundle) {
        textWantlist.setText(bundle.getString(STATE_WANTLIST));
        textWantlistcount.setText(bundle.getString(STATE_WANTLIST_COUNT));
    }
}
