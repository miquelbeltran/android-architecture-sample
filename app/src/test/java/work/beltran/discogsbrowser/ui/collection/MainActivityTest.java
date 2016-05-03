package work.beltran.discogsbrowser.ui.collection;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.ui.TestApp;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApp.class)
public class MainActivityTest {
    MainActivity activity;
    RecordsAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = TestApp.getMockAdapter();
        activity = Robolectric.setupActivity(MainActivity.class);
    }
    
    @Test
    public void testAttached() throws Exception {
        verify(adapter).onAttachedToRecyclerView((RecyclerView) any());
    }

    @Test
    public void testOnDestroy() throws Exception {
        activity.onDestroy();
        verify(adapter).activityOnDestroy();
    }

    @Test
    public void testLoadMore() throws Exception {
        RecyclerView view = (RecyclerView) activity.findViewById(R.id.records_recycler_view);
        activity.onLoadMore(view);
        verify(adapter).loadMore();
    }
}
