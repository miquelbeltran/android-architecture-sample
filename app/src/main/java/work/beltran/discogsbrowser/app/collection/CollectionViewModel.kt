package work.beltran.discogsbrowser.app.collection

import android.util.Log
import com.example.work.beltran.discogsbrowser.domain.recordcollection.RecordCollectionUseCase
import com.example.work.beltran.discogsbrowser.domain.recordcollection.RecordCollectionUseCaseParams
import work.beltran.discogsbrowser.app.base.BaseViewModel
import work.beltran.discogsbrowser.app.common.RecordItem
import work.beltran.discogsbrowser.business.mappers.Mapper
import work.beltran.discogsbrowser.business.model.Record
import javax.inject.Inject

class CollectionViewModel
@Inject constructor(private val recordCollectionUseCase: RecordCollectionUseCase,
                    private val mapper: Mapper<Record, RecordItem>):
        BaseViewModel<CollectionView>() {

    init {
        Log.d(TAG, "init ViewModel")
        fetchRecords()
        subscribeToData()
    }

    private fun subscribeToData() {
        Log.d(TAG, "Subscribed to use case")
        disposables.add(recordCollectionUseCase.getStream()
                .map(this::recordListToView)
                .doOnNext { data.onNext(it) }
                .subscribe())
    }

    private fun fetchRecords() {
        Log.d(TAG, "Ask use case to fetch records")
        disposables.add(recordCollectionUseCase.fetch(RecordCollectionUseCaseParams()).subscribe())
    }

    private fun recordListToView(records: List<Record>): CollectionView =
            CollectionView(mapper.mapTo(records))

    companion object {
        private const val TAG = "CollectionViewModel"
    }
}