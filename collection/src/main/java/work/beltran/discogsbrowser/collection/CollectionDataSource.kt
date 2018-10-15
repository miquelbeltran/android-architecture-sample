package work.beltran.discogsbrowser.collection

import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import work.beltran.discogsbrowser.api.CollectionItemsByFolderResponse
import work.beltran.discogsbrowser.api.DiscogsService

class CollectionDataSource(private val service: DiscogsService) : PageKeyedDataSource<Int, Album>() {

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