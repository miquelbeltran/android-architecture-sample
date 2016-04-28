package com.beltranfebrer.discogsbrowser.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.beltranfebrer.discogsbrowser.BuildConfig;
import com.beltranfebrer.discogsbrowser.api.UserCollection;
import com.beltranfebrer.discogsbrowser.api.model.MockRecordCollection;
import com.beltranfebrer.discogsbrowser.api.model.Record;
import com.beltranfebrer.discogsbrowser.api.model.RecordCollection;
import com.squareup.picasso.Picasso;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import rx.Observable;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 28.04.16.
 * More on http://beltranfebrer.com
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class RecordsAdapterTest {
    RecordsAdapter adapter;

    @Mock
    UserCollection userCollection;

    @Mock
    Picasso picasso;
    private rx.subjects.ReplaySubject<com.beltranfebrer.discogsbrowser.api.model.Record> subject;

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

    @Ignore
    @Test
    public void testCreateRecordViewHolder() throws Exception {
        ViewGroup viewGroup = mock(ViewGroup.class);
        RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(viewGroup, 1);
        assertThat(holder).isInstanceOf(RecordsAdapter.RecordViewHolder.class);

    }
}
