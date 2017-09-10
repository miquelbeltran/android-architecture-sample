package work.beltran.discogsbrowser.app.collection

import com.example.work.beltran.discogsbrowser.domain.recordcollection.RecordCollectionUseCase
import com.example.work.beltran.discogsbrowser.domain.recordcollection.RecordCollectionUseCaseParams
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import work.beltran.discogsbrowser.app.common.RecordToRecordItemMapper

class CollectionViewModelTest {

    private lateinit var viewModel: CollectionViewModel
    private val mapper = RecordToRecordItemMapper()

    @Mock
    private lateinit var recordCollectionUseCase: RecordCollectionUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(recordCollectionUseCase.getStream())
                .thenReturn(Flowable.just(listOf(RECORD)))
        Mockito.`when`(recordCollectionUseCase.fetch(RecordCollectionUseCaseParams()))
                .thenReturn(Completable.complete())
        viewModel = CollectionViewModel(recordCollectionUseCase, mapper)
    }

    @Test
    fun get_initial_collection_view_data_from_the_view_model() {
        viewModel.data
                .test()
                .assertValue { it.collection.contains(mapper.mapTo(RECORD)) }
    }
}