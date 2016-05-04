package work.beltran.discogsbrowser.api;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.api.model.MockRecordCollection;
import work.beltran.discogsbrowser.api.model.Record;
import work.beltran.discogsbrowser.api.model.RecordCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.WantedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class UserCollectionTest {
    private UserCollection userCollection;
    private RecordCollection collection;
    private DiscogsService service;
    UserCollectionModule module = new UserCollectionModule(Schedulers.immediate(), Schedulers.immediate());
    private rx.Observable<work.beltran.discogsbrowser.api.model.UserIdentity> mockObservableIdentity;

    @Before
    public void setUp() throws Exception {
        service = mock(DiscogsService.class);
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUsername("test");
        userIdentity.setId(1);
        mockObservableIdentity = Observable.just(userIdentity);
        when(service.getUserIdentity()).thenReturn(mockObservableIdentity);
        collection = new MockRecordCollection().recordCollection;
        Observable<RecordCollection> mockObservable = Observable.just(collection);
        Observable<WantedList> mockWanted = Observable.just(new WantedList());
        createUserCollectionWithObservable(mockObservable, mockWanted);
    }

    private void createUserCollectionWithObservable(Observable<RecordCollection> mockObservable, Observable<WantedList> mockWanted) {
        when(service.listRecords("test", 1)).thenReturn(mockObservable);
        when(service.listRecords("test", 2)).thenReturn(mockObservable);
        when(service.getWantedList("test", 1)).thenReturn(mockWanted);
        userCollection = module.provideUserCollection(service);
        userCollection.loadMoreCollection();
    }

    @Test
    public void testSubscribeAndNext() throws Exception {
        verify(service).listRecords("test", 1);
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        userCollection.getCollectionRecords().subscribe(subscriber);
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
        createUserCollectionWithObservable(mockObservable, Observable.just(new WantedList()));
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        userCollection.getCollectionRecords().subscribe(subscriber);
        subscriber.assertError(throwable);
    }

    @Test
    public void testLoadMore() throws Exception {
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        userCollection.getCollectionRecords().subscribe(subscriber);
        assertThat(subscriber.getOnNextEvents().size()).isEqualTo(1);
        assertThat(subscriber.getOnCompletedEvents().size()).isEqualTo(0);
        collection.getPagination().setPage(2);
        userCollection.loadMoreCollection();
        subscriber.assertNoErrors();
        assertThat(subscriber.getOnNextEvents().size()).isEqualTo(2);
        //assertThat(subscriber.getOnCompletedEvents().size()).isEqualTo(1);

    }

    @Test
    public void testUserIdentity() throws Exception {
        verify(service).getUserIdentity();
        TestSubscriber<UserIdentity> subscriber = new TestSubscriber<>();
        userCollection.getUserIdentity().subscribe(subscriber);
        UserIdentity identity = subscriber.getOnNextEvents().get(0);
        assertThat(identity.getUsername()).matches("test");
        assertThat(identity.getId()).isEqualTo(1);
    }

    @Test
    public void testUserOnError() throws Exception {
        Throwable throwable = new Throwable();
        Observable<UserIdentity> mockObservable = Observable.error(throwable);
        when(service.getUserIdentity()).thenReturn(mockObservable);
        Observable<RecordCollection> mockObservableRecords = Observable.just(collection);
        createUserCollectionWithObservable(mockObservableRecords, Observable.just(new WantedList()));
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        userCollection.getCollectionRecords().subscribe(subscriber);
        subscriber.assertError(throwable);
    }
}
