package work.beltran.discogsbrowser.ui.main.collection;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;
import rx.subjects.TestSubject;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.model.MockRecordCollection;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.ui.errors.ErrorPresenter;
import work.beltran.discogsbrowser.ui.main.common.UserRecordsAdapterOld;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class UserRecordsAdapterOldTest {
    UserRecordsAdapterOld adapter;
    MockRecordCollection recordCollection = new MockRecordCollection();

//    @Mock
//    RecordsApi recordsApi;

    @Mock
    ErrorPresenter presenter;

    @Mock
    Picasso picasso;

    private TestSubject<UserCollection> subject;
    private TestScheduler scheduler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        scheduler = Schedulers.test();
        subject =  TestSubject.create(scheduler);
//        when(recordsApi.getRecordsFromService(1)).thenReturn(subject);
//        adapter = new CollectionRecordsAdapterOld(recordsApi);
        adapter.setErrorPresenter(presenter);
        adapter.setPicasso(picasso);
    }

    @Test
    public void testSubscription() throws Exception {
        assertThat(adapter.getItemCount()).isEqualTo(1);
        subject.onNext(new MockRecordCollection().userCollection);
        scheduler.triggerActions();
        assertThat(adapter.getItemCount()).isEqualTo(2);
        subject.onCompleted();
        scheduler.triggerActions();
        assertThat(adapter.getItemCount()).isEqualTo(1);
    }

    @Test
    public void testOnError() throws Exception {
        // We show the progressbar
        assertThat(adapter.getItemCount()).isEqualTo(1);
        Throwable e = new Throwable();
        subject.onError(e);
        scheduler.triggerActions();
        // No progressbar after errors
        assertThat(adapter.getItemCount()).isEqualTo(0);
        verify(presenter).onError(e);
    }

    @Test
    public void testGetItemType() throws Exception {
        subject.onNext(new MockRecordCollection().userCollection);
        scheduler.triggerActions();
        assertThat(adapter.getItemViewType(0)).isEqualTo(1);
        assertThat(adapter.getItemViewType(1)).isEqualTo(0);
    }

    @Ignore
    @Test
    public void testLoadMore() throws Exception {
        adapter.loadMore();
//        verify(recordsApi).loadMoreData();
    }

    @Test
    public void testOnDestroy() throws Exception {
        // Only one item, the progressbar
        assertThat(adapter.getItemCount()).isEqualTo(1);
        adapter.activityOnDestroy();
        // we are unsubscribed, so no more records added
        subject.onNext(new MockRecordCollection().userCollection);
        scheduler.triggerActions();
        // Also the progressbar is gone
        assertThat(adapter.getItemCount()).isEqualTo(0);
    }

    @Test
    public void testCreateRecordViewHolder() throws Exception {
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(recyclerView, 1);
        assertThat(holder).isInstanceOf(UserRecordsAdapterOld.RecordViewHolder.class);
//        assertThat(((UserRecordsAdapterOld.RecordViewHolder) holder).getBinding()).isNotNull();
    }

    @Test
    public void testCreateProgressViewHolder() throws Exception {
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(recyclerView, 0);
        assertThat(holder).isInstanceOf(UserRecordsAdapterOld.ProgressBarViewHolder.class);
    }

    @NonNull
    private RecyclerView getRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(RuntimeEnvironment.application);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RuntimeEnvironment.application);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }
}
