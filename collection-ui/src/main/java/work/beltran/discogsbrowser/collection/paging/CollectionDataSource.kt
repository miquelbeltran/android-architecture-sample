package work.beltran.discogsbrowser.collection.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import arrow.core.Either
import arrow.core.left
import kotlinx.coroutines.experimental.*
import work.beltran.discogsbrowser.collection.data.GetCollectionUseCase
import work.beltran.discogsbrowser.common.domain.Album

class CollectionDataSource(
    private val collectionUseCase: GetCollectionUseCase
) : PageKeyedDataSource<Int, Album>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {
        launch(Dispatchers.IO) {
            val result = collectionUseCase.getCollectionPage("0", 1)
            when (result) {
                is Either.Left -> Log.e("CollectionDataSource", result.a)
                is Either.Right -> {
                    callback.onResult(
                        result.b.data,
                        0,
                        result.b.totalItems,
                        null,
                        result.b.nextPage
                    )
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        launch(Dispatchers.IO) {
            val result = collectionUseCase.getCollectionPage("0", params.key)
            when (result) {
                is Either.Left -> Log.e("CollectionDataSource", result.a)
                is Either.Right -> {
                    callback.onResult(
                        result.b.data,
                        result.b.nextPage
                    )
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {

    }
}