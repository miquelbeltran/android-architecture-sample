package work.beltran.discogsbrowser.app.collection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eyeem.recyclerviewtools.LoadMoreOnScrollListener;
import com.eyeem.recyclerviewtools.adapter.WrapAdapter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.common.CircleTransform;
import work.beltran.discogsbrowser.app.common.RecordsAdapter;
import work.beltran.discogsbrowser.app.common.RecordsAdapterFrameLayout;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
@SuppressLint("ViewConstructor")
public class CollectionFrameLayout extends RecordsAdapterFrameLayout<CollectionPresenter>
        implements CollectionView {

    Header header = new Header();
    Footer footer = new Footer();

    @Inject
    public Picasso picasso;

    public CollectionFrameLayout(Context context, int id) {
        super(context);
        setId(id);
        init();
    }

    @Inject
    public void setAdapter(RecordsAdapter adapter) {
        this.adapter = adapter;
        createHeaderFooter(adapter);
    }

    private void init() {
        View view = inflate(getContext(), R.layout.view_collection, this);
        ButterKnife.bind(view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new LoadMoreOnScrollListener(this));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.attachView(this);
    }

    @Override
    public void display(UserProfile userProfile) {
        header.textUser.setText(userProfile.getUsername());
        picasso.load(userProfile.getAvatarUrl())
                .placeholder(R.drawable.ic_account_circle_black_48px)
                .fit()
                .centerCrop()
                .transform(new CircleTransform())
                .into(header.imageAvatar);
        header.textCollectionCount.setText(
                getResources().getString(
                        R.string.in_collection,
                        userProfile.getNumCollection()));
    }

    @Override
    public void setLoading(boolean loading) {
        footer.progressBar.setVisibility(loading ? VISIBLE : GONE);
    }

    private void createHeaderFooter(RecordsAdapter adapter) {
        WrapAdapter wrapAdapter = new WrapAdapter(adapter);
        recyclerView.setAdapter(wrapAdapter);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header, recyclerView, false);
        ButterKnife.bind(this.header, header);
        wrapAdapter.addHeader(header);
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.footer, recyclerView, false);
        ButterKnife.bind(this.footer, footer);
        wrapAdapter.addFooter(footer);
    }

    @Inject
    @Override
    public void setPresenter(CollectionPresenter presenter) {
        this.presenter = presenter;
    }

    public static class Header {
        @BindView(R.id.text_user)
        TextView textUser;
        @BindView(R.id.image_avatar)
        ImageView imageAvatar;
        @BindView(R.id.text_collection_count)
        TextView textCollectionCount;
    }

    public static class Footer {
        @BindView(R.id.progressbar)
        ProgressBar progressBar;
    }
}
