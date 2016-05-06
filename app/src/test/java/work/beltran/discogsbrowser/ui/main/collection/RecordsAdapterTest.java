package work.beltran.discogsbrowser.ui.main.collection;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import rx.subjects.ReplaySubject;
import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.api.model.MockRecordCollection;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.api.network.RecordsSubject;
import work.beltran.discogsbrowser.ui.errors.ErrorPresenter;
import work.beltran.discogsbrowser.ui.main.common.RecordsAdapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltran.work
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class RecordsAdapterTest {
    RecordsAdapter adapter;
    MockRecordCollection recordCollection = new MockRecordCollection();

    @Mock
    RecordsSubject recordsSubject;

    @Mock
    ErrorPresenter presenter;

    @Mock
    Picasso picasso;
    private rx.subjects.ReplaySubject<Record> subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = ReplaySubject.create();
        when(recordsSubject.getSubject()).thenReturn(subject);
        adapter = new CollectionRecordsAdapter(recordsSubject, picasso);
        adapter.setErrorPresenter(presenter);
    }

    @Test
    public void testSubscription() throws Exception {
        assertThat(adapter.getItemCount()).isEqualTo(1);
        subject.onNext(new Record());
        assertThat(adapter.getItemCount()).isEqualTo(2);
        subject.onCompleted();
        assertThat(adapter.getItemCount()).isEqualTo(1);
    }

    @Test
    public void testOnError() throws Exception {
        assertThat(adapter.getItemCount()).isEqualTo(1);
        Throwable e = new Throwable();
        subject.onError(e);
        assertThat(adapter.getItemCount()).isEqualTo(1);
        verify(presenter).onError(e);
    }

    @Test
    public void testGetItemType() throws Exception {
        subject.onNext(new Record());
        assertThat(adapter.getItemViewType(0)).isEqualTo(1);
        assertThat(adapter.getItemViewType(1)).isEqualTo(0);
    }

    @Test
    public void testLoadMore() throws Exception {
        adapter.loadMore();
        verify(recordsSubject).loadMoreData();
    }

    @Test
    public void testOnDestroy() throws Exception {
        assertThat(adapter.getItemCount()).isEqualTo(1);
        adapter.activityOnDestroy();
        // we are unsubscribed, so no more records added
        subject.onNext(new Record());
        assertThat(adapter.getItemCount()).isEqualTo(1);
    }

    @Test
    public void testCreateRecordViewHolder() throws Exception {
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(recyclerView, 1);
        assertThat(holder).isInstanceOf(RecordsAdapter.RecordViewHolder.class);
        assertThat(((RecordsAdapter.RecordViewHolder) holder).getBinding()).isNotNull();
    }

    @Test
    public void testCreateProgressViewHolder() throws Exception {
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(recyclerView, 0);
        assertThat(holder).isInstanceOf(RecordsAdapter.ProgressBarViewHolder.class);
    }

    @NonNull
    private RecyclerView getRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(RuntimeEnvironment.application);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RuntimeEnvironment.application);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }
}
