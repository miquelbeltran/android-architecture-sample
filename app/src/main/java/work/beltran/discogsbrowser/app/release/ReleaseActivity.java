package work.beltran.discogsbrowser.app.release;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.app.App;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;
import work.beltran.discogsbrowser.app.di.ApiComponent;
import work.beltran.discogsbrowser.app.main.MainActivity;

public class ReleaseActivity extends AppCompatActivity implements ReleaseView {

    private static final String EXTRA_RECORD = "EXTRA_RECORD";
    private static final String TAG = ReleaseActivity.class.getName();
    ActionBar actionBar;

    @Nullable
    @Inject
    public ReleasePresenter presenter;
    @Inject
    @Nullable
    public Picasso picasso;

    @BindView(R.id.image_album)
    ImageView imageAlbum;
    @BindView(R.id.text_artist)
    TextView textArtist;
    @BindView(R.id.text_year)
    TextView textYear;
    @BindView(R.id.text_format)
    TextView textFormat;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.button_remove_from_collection)
    Button buttonRemoveFromCollection;
    @BindView(R.id.button_add_to_wantlist)
    Button buttonAddToWantlist;
    @BindView(R.id.button_remove_from_wantlist)
    Button buttonRemoveFromWantlist;


    public static Intent createReleaseActivity(Context context,
                                               RecordAdapterItem record) {
        Intent intent = new Intent(context, ReleaseActivity.class);
        intent.putExtra(EXTRA_RECORD, record);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        ButterKnife.bind(this);
        ApiComponent component = ((App) getApplication()).getApiComponent();
        if (component != null) {
            component.inject(this);
        }
        RecordAdapterItem recordAdapterItem = getIntent().getParcelableExtra(EXTRA_RECORD);
        if (presenter != null) {
            presenter.attachView(this);
            presenter.setReleaseId(recordAdapterItem.getReleaseId());
        }
        initActionBar(recordAdapterItem);
        display(recordAdapterItem);
    }

    @Override
    public void display(RecordAdapterItem recordAdapterItem) {
        textArtist.setText(recordAdapterItem.getArtist());
        textYear.setText(recordAdapterItem.getYear());
        textFormat.setText(recordAdapterItem.getFormat());
        if (picasso != null) {
            picasso.load(recordAdapterItem.getThumb())
                    .fit()
                    .centerCrop()
                    .into(imageAlbum);
        }
        setAddToCollectionVisible(!recordAdapterItem.isInCollection());
        setRemoveFromCollectionVisible(recordAdapterItem.isInCollection());
    }

    @Override
    public void broadcastAddToCollection() {
        RecordAdapterItem recordAdapterItem = getIntent().getParcelableExtra(EXTRA_RECORD);
        Intent intent = new Intent(MainActivity.INTENT_FILTER_ADD_COL)
                .putExtra(MainActivity.INTENT_FILTER_RECORD_ITEM_EXTRA, recordAdapterItem);
        sendBroadcast(intent);
    }

    @Override
    public void broadcastRemoveFromCollection() {
        RecordAdapterItem recordAdapterItem = getIntent().getParcelableExtra(EXTRA_RECORD);
        Intent intent = new Intent(MainActivity.INTENT_FILTER_REMOVE_COL)
                .putExtra(MainActivity.INTENT_FILTER_RECORD_ITEM_EXTRA, recordAdapterItem);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private void initActionBar(RecordAdapterItem recordAdapterItem) {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(recordAdapterItem.getTitle());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAddToCollectionVisible(boolean isVisible) {
        floatingActionButton.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setRemoveFromCollectionVisible(boolean isVisible) {
        buttonRemoveFromCollection.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayError(@StringRes int messageId) {

    }

    @OnClick(R.id.button_add_to_wantlist)
    public void addToWantlist() {
        if (presenter != null) {
            presenter.addToWantlist();
        }
    }

    @OnClick(R.id.button_remove_from_wantlist)
    public void removeFromWantlist() {
        if (presenter != null) {
            presenter.removeFromWantlist();
        }
    }

    @OnClick(R.id.button_remove_from_collection)
    public void removeFromCollection() {
        if (presenter != null) {
            presenter.removeFromCollection();
        }
    }

    @OnClick(R.id.floatingActionButton)
    public void addToCollection() {
        if (presenter != null) {
            presenter.addToCollection();
        }
    }
}
