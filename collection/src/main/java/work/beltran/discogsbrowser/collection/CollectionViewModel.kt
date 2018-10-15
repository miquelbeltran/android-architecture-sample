package work.beltran.discogsbrowser.collection

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import work.beltran.discogsbrowser.api.CollectionItemsByFolderResponse
import work.beltran.discogsbrowser.api.provideService

class CollectionViewModel : ViewModel() {

    val liveData = LivePagedListBuilder(
        CollectionDataSourceFactory(), PagedList.Config.Builder()
            .setPageSize(20)
            .build()
    ).build()

    fun load() {

//        liveData.value = LivePagedListBuilder()

    }
}

class CollectionDataSourceFactory : DataSource.Factory<Int, Album>() {
    override fun create(): DataSource<Int, Album> {
        return CollectionDataSource()
    }
}

class CollectionDataSource : PageKeyedDataSource<Int, Album>() {

    val service = provideService()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {

        service.getCollectionItemsByFolder(
            username = "mike513",
            folderId = "0",
            page = 1,
            perPage = 20
        ).enqueue(object : Callback<CollectionItemsByFolderResponse> {
            override fun onFailure(call: Call<CollectionItemsByFolderResponse>, t: Throwable) {
                error(t)
            }

            override fun onResponse(
                call: Call<CollectionItemsByFolderResponse>,
                response: Response<CollectionItemsByFolderResponse>
            ) {

                response.body()?.let { body ->
                    val albums = body.releases.map {
                        Album(it.id, it.id)
                    }
                    val nextPage = if (body.pagination.page < body.pagination.pages) body.pagination.page + 1 else null
                    callback.onResult(
                        albums, 0, body.pagination.items, null, nextPage
                    )
                }
            }

        })


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {

        service.getCollectionItemsByFolder(
            username = "mike513",
            folderId = "0",
            page = params.key,
            perPage = 20
        ).enqueue(object : Callback<CollectionItemsByFolderResponse> {
            override fun onFailure(call: Call<CollectionItemsByFolderResponse>, t: Throwable) {
                error(t)
            }

            override fun onResponse(
                call: Call<CollectionItemsByFolderResponse>,
                response: Response<CollectionItemsByFolderResponse>
            ) {

                response.body()?.let { body ->
                    val albums = body.releases.map {
                        Album(it.id, it.id)
                    }
                    val nextPage = if (body.pagination.page < body.pagination.pages) body.pagination.page + 1 else null
                    callback.onResult(
                        albums, nextPage
                    )
                }
            }

        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {

    }
}

