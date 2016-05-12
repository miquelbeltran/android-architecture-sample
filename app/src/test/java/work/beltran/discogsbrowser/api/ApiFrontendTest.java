package work.beltran.discogsbrowser.api;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import work.beltran.discogsbrowser.api.di.modules.UserCollectionModule;
import work.beltran.discogsbrowser.api.model.MockRecordCollection;
import work.beltran.discogsbrowser.api.model.SearchRecord;
import work.beltran.discogsbrowser.api.model.SearchResults;
import work.beltran.discogsbrowser.api.model.UserCollection;
import work.beltran.discogsbrowser.api.model.UserIdentity;
import work.beltran.discogsbrowser.api.model.UserProfile;
import work.beltran.discogsbrowser.api.model.UserWanted;
import work.beltran.discogsbrowser.api.model.record.Record;
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
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("test");
        userProfile.setAvatar_url("url");
        userProfile.setNum_collection(2);
        userProfile.setNum_wantlist(3);

        mockObservableIdentity = Observable.just(userIdentity);
        when(service.getUserIdentity()).thenReturn(mockObservableIdentity);
        when(service.getUserProfile("test")).thenReturn(Observable.just(userProfile));
        SearchResults results = new SearchResults();
        results.setPagination(new MockRecordCollection().userCollection.getPagination());
        SearchRecord searchRecord = new SearchRecord();
        searchRecord.setTitle("name - title");
        searchRecord.setThumb("url");
        searchRecord.setFormat(new ArrayList<String>(Arrays.asList("CD")));
        searchRecord.setId(2);
        results.setSearchRecords(new ArrayList<SearchRecord>(Arrays.asList(searchRecord)));
        when(service.search("test", "release", null, null)).thenReturn(Observable.just(results));
        collection = new MockRecordCollection().userCollection;
        Observable<UserCollection> mockObservable = Observable.just(collection);
        UserWanted wanted = new UserWanted();
        wanted.setRecords(new MockRecordCollection().userCollection.getRecords());
        wanted.setPagination(new MockRecordCollection().userCollection.getPagination());
        Observable<UserWanted> mockWanted = Observable.just(wanted);
        createUserCollectionWithObservable(mockObservable, mockWanted);
    }

    private void createUserCollectionWithObservable(Observable<UserCollection> mockObservable, Observable<UserWanted> mockWanted) {
        when(service.listRecords("test", 1)).thenReturn(mockObservable);
        when(service.listRecords("test", 2)).thenReturn(mockObservable);
        when(service.getWantedList("test", 1)).thenReturn(mockWanted);
        apiFrontend = module.provideUserCollection(service);
//        apiFrontend.loadMoreCollection();
    }

    @Test
    public void testSearch() throws Exception {
        TestSubscriber<Record> subscriber = new TestSubscriber<>();
        apiFrontend.getSearchSubject().search("test", 1).subscribe(subscriber);
        verify(service).search("test", "release", null, null);
        Record record = subscriber.getOnNextEvents().get(0);
        assertThat(record.getBasicInformation().getArtists().get(0).getName()).matches("name");
        assertThat(record.getBasicInformation().getTitle()).matches("title");
        assertThat(record.getBasicInformation().getFormats().get(0).getName()).matches("CD");
        assertThat(record.getBasicInformation().getThumb()).matches("url");
        assertThat(record.getInstance_id()).isEqualTo(2);
    }

    @Test
    public void testWantList() throws Exception {
        TestSubscriber<UserWanted> subscriber = new TestSubscriber<>();
        apiFrontend.getWantedRecords().getRecordsFromService(1).subscribe(subscriber);
        verify(service).getWantedList("test", 1);
        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        UserWanted collection = subscriber.getOnNextEvents().get(0);
        Record record = collection.getRecords().get(0);
        assertThat(record.getInstance_id()).isEqualTo(1234);
        assertThat(record.getBasicInformation().getThumb()).matches("thumb url");
    }

    @Test
    public void testSubscribeAndNext() throws Exception {
        TestSubscriber<UserCollection> subscriber = new TestSubscriber<>();
        apiFrontend.getCollectionRecords().getRecordsFromService(1).subscribe(subscriber);
        verify(service).listRecords("test", 1);
        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        UserCollection collection = subscriber.getOnNextEvents().get(0);
        Record record = collection.getRecords().get(0);
        assertThat(record.getInstance_id()).isEqualTo(1234);
        assertThat(record.getBasicInformation().getThumb()).matches("thumb url");
    }

    @Test
    public void testOnError() throws Exception {
        Throwable throwable = new Throwable();
        Observable<UserCollection> mockObservable = Observable.error(throwable);
        createUserCollectionWithObservable(mockObservable, Observable.just(new UserWanted()));
        TestSubscriber<UserCollection> subscriber = new TestSubscriber<>();
        apiFrontend.getCollectionRecords().getRecordsFromService(1).subscribe(subscriber);
        subscriber.assertError(throwable);
    }

    @Test
    public void testLoadMore() throws Exception {
        TestSubscriber<UserCollection> subscriber = new TestSubscriber<>();
        apiFrontend.getCollectionRecords().getRecordsFromService(1).subscribe(subscriber);
        assertThat(subscriber.getOnNextEvents().size()).isEqualTo(1);
        assertThat(subscriber.getOnCompletedEvents().size()).isEqualTo(1);
        collection.getPagination().setPage(2);
        // Load page 2
        apiFrontend.getCollectionRecords().getRecordsFromService(2).subscribe(subscriber);
        subscriber.assertNoErrors();
        assertThat(subscriber.getOnNextEvents().size()).isEqualTo(1);
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
    public void testUserProfile() throws Exception {
        TestSubscriber<UserProfile> subscriber = new TestSubscriber<>();
        apiFrontend.getUserProfile().subscribe(subscriber);
        UserProfile identity = subscriber.getOnNextEvents().get(0);
        assertThat(identity.getUsername()).matches("test");
        assertThat(identity.getAvatar_url()).matches("url");
        assertThat(identity.getNum_wantlist()).isEqualTo(3);
        assertThat(identity.getNum_collection()).isEqualTo(2);
    }

    @Test
    public void testUserOnError() throws Exception {
        Throwable throwable = new Throwable();
        Observable<UserIdentity> mockObservable = Observable.error(throwable);
        when(service.getUserIdentity()).thenReturn(mockObservable);
        Observable<UserCollection> mockObservableRecords = Observable.just(collection);
        createUserCollectionWithObservable(mockObservableRecords, Observable.just(new UserWanted()));
        TestSubscriber<UserCollection> subscriber = new TestSubscriber<>();
        apiFrontend.getCollectionRecords().getRecordsFromService(1).subscribe(subscriber);
        subscriber.assertError(throwable);
    }
}
