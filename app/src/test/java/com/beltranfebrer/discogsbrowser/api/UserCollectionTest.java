package com.beltranfebrer.discogsbrowser.api;

import com.beltranfebrer.discogsbrowser.api.di.modules.UserCollectionModule;
import com.beltranfebrer.discogsbrowser.api.model.MockRecordCollection;
import com.beltranfebrer.discogsbrowser.api.model.Record;
import com.beltranfebrer.discogsbrowser.api.model.RecordCollection;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltranfebrer.com
 */
public class UserCollectionTest {
    private UserCollection userCollection;
    private DiscogsService service;
    UserCollectionModule module = new UserCollectionModule("test", Schedulers.immediate(), Schedulers.immediate());

    @Before
    public void setUp() throws Exception {
        service = mock(DiscogsService.class);
        Observable<RecordCollection> mockObservable = Observable.just(new MockRecordCollection().recordCollection);
        createUserCollectionWithObservable(mockObservable);
    }

    private void createUserCollectionWithObservable(Observable<RecordCollection> mockObservable) {
        when(service.listRecords("test", 1)).thenReturn(mockObservable);
        userCollection = module.provideUserCollection(service);
    }

    @Test
    public void testSubscribeAndNext() throws Exception {
        verify(service).listRecords("test", 1);
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        userCollection.getSubject().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        Record record = subscriber.getOnNextEvents().get(0);
        assertThat(record.getInstance_id()).matches("1234");
        assertThat(record.getBasicInformation().getThumb()).matches("thumb url");
    }

    @Test
    public void testOnError() throws Exception {
        Throwable throwable = new Throwable();
        Observable<RecordCollection> mockObservable = Observable.error(throwable);
        createUserCollectionWithObservable(mockObservable);
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        userCollection.getSubject().subscribe(subscriber);
        subscriber.assertError(throwable);
    }
}
