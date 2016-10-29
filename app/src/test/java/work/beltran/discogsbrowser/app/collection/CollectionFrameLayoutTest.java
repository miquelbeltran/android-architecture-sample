package work.beltran.discogsbrowser.app.collection;

import android.view.View;

import com.squareup.picasso.Picasso;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.app.common.RecordAdapterItem;
import work.beltran.discogsbrowser.app.common.RecordsAdapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static work.beltran.discogsbrowser.app.collection.DiscogsModelMocks.getUserProfile;

/**
 * Created by Miquel Beltran on 8/28/16
 * More on http://beltran.work
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class, sdk = 22)
public class CollectionFrameLayoutTest {
    CollectionFrameLayout view;
    @Mock
    private CollectionPresenter presenter;
    @Mock
    private RecordsAdapter adapter;

    @Before
    public void setUp() throws Exception {
        view = new CollectionFrameLayout(RuntimeEnvironment.application, R.id.CollectionView);
        MockitoAnnotations.initMocks(this);
        view.setPresenter(presenter);
        view.setAdapter(adapter);
        view.picasso = Picasso.with(RuntimeEnvironment.application);
    }

    @Test
    public void displayError() throws Exception {
        view.displayError(0);
    }

    @Test
    public void onAttachedToWindow() throws Exception {
        reset(presenter);
        view.onAttachedToWindow();
        verify(presenter).attachView(view);
    }

    @Test
    public void onDetachedFromWindow() throws Exception {
        view.onDetachedFromWindow();
        verify(presenter).detachView();
    }

    @Test
    public void onLoadMore() throws Exception {
        view.onLoadMore(null);
        verify(presenter).loadMore();
    }

    @Test
    public void addRecords() throws Exception {
        List<RecordAdapterItem> listRecords = new ArrayList<>();
        view.addRecords(listRecords);
        verify(adapter).addItems(listRecords);
    }

    @Test
    public void display() throws Exception {
        UserProfile userProfile = getUserProfile();
        view.display(userProfile);
        assertThat(view.header.textUser.getText().toString())
                .isEqualTo(DiscogsModelMocks.USERNAME);
        assertThat(view.header.textCollectionCount.getText().toString())
                .isEqualTo(DiscogsModelMocks.NUM_COLLECTION + " in Collection");
    }

    @Test
    public void setLoading() throws Exception {
        view.setLoading(true);
        assertThat(view.footer.progressBar.getVisibility()).isEqualTo(View.VISIBLE);
        view.setLoading(false);
        assertThat(view.footer.progressBar.getVisibility()).isEqualTo(View.GONE);
    }
}