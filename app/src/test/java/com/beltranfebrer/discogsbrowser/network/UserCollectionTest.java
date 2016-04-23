package com.beltranfebrer.discogsbrowser.network;

import com.beltranfebrer.discogsbrowser.MockRecordCollection;
import com.beltranfebrer.discogsbrowser.UserCollection;
import com.beltranfebrer.discogsbrowser.model.Record;
import com.beltranfebrer.discogsbrowser.model.RecordCollection;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.Observer;

import static junit.framework.Assert.fail;
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

    @Before
    public void setUp() throws Exception {
        service = mock(DiscogsService.class);
        Observable<RecordCollection> mockObservable = Observable.just(new MockRecordCollection().recordCollection);
        when(service.listRecords("test")).thenReturn(mockObservable);
        userCollection = new UserCollection(service, "test");
    }

    @Test
    public void testSubscribeAndNext() throws Exception {
        userCollection.subject.subscribe(new Observer<Record>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                fail();
            }

            @Override
            public void onNext(Record record) {
                assertThat(record.getInstance_id()).matches("1234");
            }
        });
        verify(service).listRecords("test");
    }
}
