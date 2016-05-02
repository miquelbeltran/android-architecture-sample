package work.beltran.discogsbrowser.ui.collection;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import work.beltran.discogsbrowser.BuildConfig;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.UserCollection;
import work.beltran.discogsbrowser.api.model.MockRecordCollection;
import work.beltran.discogsbrowser.api.model.Record;
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

import rx.subjects.ReplaySubject;
import work.beltran.discogsbrowser.ui.collection.RecordsAdapter;

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
    UserCollection userCollection;

    @Mock
    Picasso picasso;
    private rx.subjects.ReplaySubject<work.beltran.discogsbrowser.api.model.Record> subject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = ReplaySubject.create();
        when(userCollection.getSubject()).thenReturn(subject);
        adapter = new RecordsAdapter(userCollection, picasso);
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
        subject.onError(new Throwable());
        assertThat(adapter.getItemCount()).isEqualTo(1);
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
        verify(userCollection).loadMore();
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

    @Ignore
    @Test
    public void testBindRecordViewHolder() throws Exception {
        subject.onNext(recordCollection.recordCollection.getRecords().get(0));
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(recyclerView, 1);
        adapter.onBindViewHolder(holder, 0);
        Thread.sleep(1000);
        TextView textView = (TextView) holder.itemView.findViewById(R.id.record_title);
        assertThat(textView.getText()).isEqualTo("Title");
    }
}
