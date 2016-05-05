package work.beltran.discogsbrowser.api;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.api.model.MockRecordCollection;
import work.beltran.discogsbrowser.api.model.record.Record;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserWanted;
import work.beltran.discogsbrowser.api.network.DiscogsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Miquel Beltran on 23.04.16.
 * More on http://beltran.work
 */
public class ApiFrontendTest {
    private ApiFrontend apiFrontend;
    private UserCollection collection;
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
        collection = new MockRecordCollection().userCollection;
        Observable<UserCollection> mockObservable = Observable.just(collection);
        Observable<UserWanted> mockWanted = Observable.just(new UserWanted());
        createUserCollectionWithObservable(mockObservable, mockWanted);
    }

    private void createUserCollectionWithObservable(Observable<UserCollection> mockObservable, Observable<UserWanted> mockWanted) {
        when(service.listRecords("test", 1)).thenReturn(mockObservable);
        when(service.listRecords("test", 2)).thenReturn(mockObservable);
        when(service.getWantedList("test", 1)).thenReturn(mockWanted);
        apiFrontend = module.provideUserCollection(service);
        apiFrontend.loadMoreCollection();
    }

    @Test
    public void testSubscribeAndNext() throws Exception {
        verify(service).listRecords("test", 1);
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        apiFrontend.getCollectionRecords().getSubject().subscribe(subscriber);
        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        Record record = subscriber.getOnNextEvents().get(0);
        assertThat(record.getInstance_id()).isEqualTo(1234);
        assertThat(record.getBasicInformation().getThumb()).matches("thumb url");
    }

    @Test
    public void testOnError() throws Exception {
        Throwable throwable = new Throwable();
        Observable<UserCollection> mockObservable = Observable.error(throwable);
        createUserCollectionWithObservable(mockObservable, Observable.just(new UserWanted()));
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        apiFrontend.getCollectionRecords().getSubject().subscribe(subscriber);
        subscriber.assertError(throwable);
    }

    @Test
    public void testLoadMore() throws Exception {
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        apiFrontend.getCollectionRecords().getSubject().subscribe(subscriber);
        assertThat(subscriber.getOnNextEvents().size()).isEqualTo(1);
        assertThat(subscriber.getOnCompletedEvents().size()).isEqualTo(0);
        collection.getPagination().setPage(2);
        apiFrontend.loadMoreCollection();
        subscriber.assertNoErrors();
        assertThat(subscriber.getOnNextEvents().size()).isEqualTo(2);
        assertThat(subscriber.getOnCompletedEvents().size()).isEqualTo(1);
    }

    @Test
    public void testUserIdentity() throws Exception {
        verify(service).getUserIdentity();
        TestSubscriber<UserIdentity> subscriber = new TestSubscriber<>();
        apiFrontend.getUserIdentity().subscribe(subscriber);
        UserIdentity identity = subscriber.getOnNextEvents().get(0);
        assertThat(identity.getUsername()).matches("test");
        assertThat(identity.getId()).isEqualTo(1);
    }

    @Test
    public void testUserOnError() throws Exception {
        Throwable throwable = new Throwable();
        Observable<UserIdentity> mockObservable = Observable.error(throwable);
        when(service.getUserIdentity()).thenReturn(mockObservable);
        Observable<UserCollection> mockObservableRecords = Observable.just(collection);
        createUserCollectionWithObservable(mockObservableRecords, Observable.just(new UserWanted()));
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        apiFrontend.getCollectionRecords().getSubject().subscribe(subscriber);
        subscriber.assertError(throwable);
    }
}
