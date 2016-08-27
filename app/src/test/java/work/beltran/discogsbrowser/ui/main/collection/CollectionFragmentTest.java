package work.beltran.discogsbrowser.ui.main.collection;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.TestApp;
import work.beltran.discogsbrowser.ui.main.common.UserRecordsAdapter;
import work.beltran.discogsbrowser.ui.main.common.RecordsFragment;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;


/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class)
public class CollectionFragmentTest {
    RecordsFragment fragment;
    UserRecordsAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = TestApp.getMockAdapter();
        fragment = CollectionFragment.newInstance();
        startFragment(fragment);
    }

    @Test
    public void testAttached() throws Exception {
        verify(adapter).onAttachedToRecyclerView((RecyclerView) any());
    }

    @Test
    public void testOnDestroy() throws Exception {
        fragment.onDestroy();
        verify(adapter).activityOnDestroy();
    }

    @Test
    public void testLoadMore() throws Exception {
        RecyclerView view = (RecyclerView) fragment.getView().findViewById(R.id.recycler_records);
        fragment.onLoadMore(view);
        verify(adapter).loadMore();
    }
}
