package work.beltran.discogsbrowser.app.collection;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.common.CircleTransform;

/**
 * Created by Miquel Beltran on 10/2/16
 * More on http://beltran.work
 */
class CollectionHeader {
    private static final String STATE_USER = "STATE_USER";
    private static final String STATE_COLLECTION_COUNT = "STATE_COLLECTION_COUNT";
    private static final String STATE_AVATAR_URL = "STATE_AVATAR_URL";
    private String avatarUrl;
    @BindView(R.id.text_user)
    TextView textUser;
    @BindView(R.id.image_avatar)
    ImageView imageAvatar;
    @BindView(R.id.text_collection_count)
    TextView textCollectionCount;

    Bundle getState() {
        Bundle bundle = new Bundle();
        bundle.putString(STATE_USER, textUser.getText().toString());
        bundle.putString(STATE_COLLECTION_COUNT, textCollectionCount.getText().toString());
        bundle.putString(STATE_AVATAR_URL, avatarUrl);
        return bundle;
    }

    void loadState(Bundle bundle, Picasso picasso) {
        textUser.setText(bundle.getString(STATE_USER));
        textCollectionCount.setText(bundle.getString(STATE_COLLECTION_COUNT));
        avatarUrl = bundle.getString(STATE_AVATAR_URL);
        loadAvatar(picasso);
    }

    void bind(UserProfile userProfile, Picasso picasso, Context context) {
        textUser.setText(userProfile.getUsername());
        avatarUrl = userProfile.getAvatarUrl();
        loadAvatar(picasso);
        textCollectionCount.setText(
                context.getResources().getString(
                        R.string.in_collection,
                        userProfile.getNumCollection()));
    }

    private void loadAvatar(Picasso picasso) {
        picasso.load(avatarUrl)
                .placeholder(R.drawable.ic_account_circle_black_48px)
                .fit()
                .centerCrop()
                .transform(new CircleTransform())
                .into(imageAvatar);
    }
}
