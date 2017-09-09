package work.beltran.discogsbrowser.app.collection

import android.util.Log
import com.example.work.beltran.discogsbrowser.domain.RecordCollectionUseCase
import com.example.work.beltran.discogsbrowser.domain.RecordCollectionUseCaseParams
import io.reactivex.Flowable
import io.reactivex.FlowableSubscriber
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.BehaviorSubject
import work.beltran.discogsbrowser.app.base.BaseViewModel
import work.beltran.discogsbrowser.app.common.RecordListItem
import work.beltran.discogsbrowser.business.CollectionRepository
import work.beltran.discogsbrowser.business.mappers.Mapper
import work.beltran.discogsbrowser.business.model.Record
import javax.inject.Inject

class CollectionViewModel
@Inject constructor(private val recordCollectionUseCase: RecordCollectionUseCase,
                    private val mapper: Mapper<Record, RecordListItem>):
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