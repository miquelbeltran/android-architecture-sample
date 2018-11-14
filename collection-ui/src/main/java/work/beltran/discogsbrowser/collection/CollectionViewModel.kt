package work.beltran.discogsbrowser.collection

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import work.beltran.discogsbrowser.collection.data.GetCollectionUseCase
import work.beltran.discogsbrowser.common.domain.Album
import kotlin.coroutines.experimental.CoroutineContext

class CollectionViewModel(
    private val collectionUseCase: GetCollectionUseCase
) : ViewModel(), CoroutineScope {

    private val contextJob = Job()
    private var loadingJob: Job? = null
    private var nextPage: Int? = null
    // Store the different network results in a map
    private val pages = HashMap<Int, List<Album>>()

    override val coroutineContext: CoroutineContext
        get() = contextJob

    val liveData = MutableLiveData<List<Album>>()

    init {
        launchGetCollectionPage(1)
    }

    private fun launchGetCollectionPage(
        page: Int
    ) {
        loadingJob = launch {
            Log.d("CollectionViewModel", "Loading page: $page")
            val result = collectionUseCase.getCollectionPage("0", page)
            when (result) {
                // TODO Show loading errors on UI
                is Either.Left -> Log.e("CollectionViewModel", result.a)
                is Either.Right -> {
                    // Update page in the pages map
                    pages[page] = result.b.data
                    // Update nextPage, it can be null
                    // this means we are at the final page
                    nextPage = result.b.nextPage
                }
            }
            // FlatMap into a single list to display results
            liveData.postValue(pages.flatMap { it.value })
            Log.d("CollectionViewModel", "Loaded! page: $page")
        }
    }

    fun loadMore() {
        // current loading job is active, then do not load more
        if (loadingJob?.isActive == true) return
        // load if next page is not null
        nextPage?.let { launchGetCollectionPage(it) }
    }

    override fun onCleared() {
        // Cancel any running job
        contextJob.cancel()
    }
}

