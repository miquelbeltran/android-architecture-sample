package work.beltran.discogsbrowser.collection

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class CollectionViewModel(
    collectionDataSourceFactory: CollectionDataSourceFactory
) : ViewModel() {

    val liveData = LivePagedListBuilder(
        collectionDataSourceFactory, PagedList.Config.Builder()
            .setPageSize(20)
            .build()
    ).build()

    fun load() {

//        liveData.value = LivePagedListBuilder()

    }
}

