package work.beltran.discogsbrowser.business.collection

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.api.model.UserCollection
import work.beltran.discogsbrowser.api.model.pagination.Pagination
import work.beltran.discogsbrowser.business.ReactiveStore
import work.beltran.discogsbrowser.business.mappers.RecordApiToRecordMapper
import work.beltran.discogsbrowser.business.model.Record
import work.beltran.discogsbrowser.test.*

class CollectionRepositoryImplTest {

    private val mapper = RecordApiToRecordMapper()
    private lateinit var repo: CollectionRepositoryImpl

    @Mock
    lateinit var store: ReactiveStore<Int, Record>
    @Mock
    lateinit var service: DiscogsService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repo = CollectionRepositoryImpl(
                service = service,
                schedulers = TestSchedulers,
                store = store,
                mapper = mapper,
                username = USERNAME
        )
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `get record from reactive store`() {
        Mockito.`when`(store.get(RECORD_ID))
                .thenReturn(Flowable.just(RECORD))
        repo.getRecord(RECORD_ID)
                .test()
                .assertValue { it == RECORD }
    }

    @Test
    fun `get all records from reactive store`() {
        Mockito.`when`(store.getAll())
                .thenReturn(Flowable.just(listOf(RECORD)))
        repo.getAllRecords()
                .test()
                .assertValue { it == listOf(RECORD) }
    }

    @Test
    fun `fetch a page of records from api and store`() {
        Mockito.`when`(service.listRecords(USERNAME, PAGE))
                .thenReturn(Single.just(
                        UserCollection(
                                pagination = Pagination(0, 1),
                                records = listOf(RECORD_API)
                        )
                ))
        repo.fetchRecords(PAGE)
                .test()
                .assertComplete()
        Mockito.verify(store).replace(listOf(RECORD))
    }

    @Test
    fun `fetch a record from api and store`() {
        Mockito.`when`(service.getRecordInCollection(USERNAME, RECORD_ID))
                .thenReturn(Single.just(
                        UserCollection(
                                pagination = Pagination(0, 1),
                                records = listOf(RECORD_API)
                        )
                ))
        repo.fetchRecord(RECORD_ID)
                .test()
                .assertComplete()
        Mockito.verify(store).replace(listOf(RECORD))
    }

    @Test
    fun `add record to collection and update on store`() {
        Mockito.`when`(service.addToCollection(USERNAME, RECORD_ID))
                .thenReturn(Completable.complete())
        Mockito.`when`(service.getRecordInCollection(USERNAME, RECORD_ID))
                .thenReturn(Single.just(
                        UserCollection(
                                pagination = Pagination(0, 1),
                                records = listOf(RECORD_API)
                        )
                ))
        repo.addRecord(RECORD_ID)
                .test()
                .assertComplete()
        Mockito.verify(store).replace(listOf(RECORD))
    }

    @Test
    fun `remove record from collection and from store`() {
        Mockito.`when`(service.getRecordInCollection(USERNAME, RECORD_ID))
                .thenReturn(Single.just(
                        UserCollection(
                                pagination = Pagination(0, 1),
                                records = listOf(RECORD_API)
                        )
                ))
        Mockito.`when`(service.removeFromCollection(USERNAME, RECORD_ID, INSTANCE_ID))
                .thenReturn(Completable.complete())
        repo.removeRecord(RECORD_ID)
                .test()
                .assertComplete()
        Mockito.verify(store).remove(RECORD)
    }

    companion object {
        private const val USERNAME = "USERNAME"
        private const val PAGE = 0
    }
}