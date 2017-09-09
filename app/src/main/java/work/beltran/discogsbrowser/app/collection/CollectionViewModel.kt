package work.beltran.discogsbrowser.app.collection

import android.util.Log
import com.example.work.beltran.discogsbrowser.domain.RecordCollectionUseCase
import com.example.work.beltran.discogsbrowser.domain.RecordCollectionUseCaseParams
import io.reactivex.Flowable
import io.reactivex.FlowableSubscriber
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import work.beltran.discogsbrowser.app.common.RecordListItem
import work.beltran.discogsbrowser.business.CollectionRepository
import work.beltran.discogsbrowser.business.mappers.Mapper
import work.beltran.discogsbrowser.business.model.Record
import javax.inject.Inject

class CollectionViewModel
@Inject constructor(private val recordCollectionUseCase: RecordCollectionUseCase,
                    private val mapper: Mapper<Record, RecordListItem>) {

    private val disposables = CompositeDisposable()

    init {
        Log.d(TAG, "init")
    }

    fun dispose() {
        disposables.clear()
    }

    fun screenData(subscriber: Consumer<CollectionView>) {
        fetchRecords()
        disposables.add(recordCollectionUseCase
                .getStream()
                .map(this::recordListToView)
                .subscribe(subscriber))
    }

    private fun fetchRecords() {
        disposables.add(recordCollectionUseCase.fetch(RecordCollectionUseCaseParams()).subscribe())
    }

    private fun recordListToView(records: List<Record>): CollectionView =
            CollectionView(mapper.mapTo(records))

    companion object {
        private const val TAG = "CollectionViewModel"
    }
}