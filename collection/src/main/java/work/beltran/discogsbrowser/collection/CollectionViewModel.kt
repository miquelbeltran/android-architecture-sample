package work.beltran.discogsbrowser.collection

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList

class CollectionViewModel : ViewModel() {

    val liveData = LivePagedListBuilder(CollectionDataSourceFactory(), PagedList.Config.Builder()
        .setPageSize(1)
        .build()).build()

    fun load() {

//        liveData.value = LivePagedListBuilder()

    }
}

class CollectionDataSourceFactory: DataSource.Factory<Int, Album>() {
    override fun create(): DataSource<Int, Album> {
        return CollectionDataSource()
    }
}

class CollectionDataSource : PageKeyedDataSource<Int, Album>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {

        callback.onResult(
            listOf(
                Album("1", "Album 1"),
                Album("2", "Album 2")
            ),0,4, null, 1
        )

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {

        callback.onResult(
            listOf(
                Album("3", "Album 3"),
                Album("4", "Album 4")
            ),
            null
        )

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {

    }
}

