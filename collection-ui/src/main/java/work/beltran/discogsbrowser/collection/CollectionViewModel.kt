package work.beltran.discogsbrowser.collection

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.cancel
import work.beltran.discogsbrowser.common.domain.Album
import kotlin.coroutines.experimental.CoroutineContext

class CollectionViewModel(
    collectionDataSourceFactory: DataSource.Factory<Int, Album>
) : ViewModel() {

    val liveData = LivePagedListBuilder(
        collectionDataSourceFactory, PagedList.Config.Builder()
            .setPageSize(20)
            .build()
    ).build()

    override fun onCleared() {
        super.onCleared()
    }
}

