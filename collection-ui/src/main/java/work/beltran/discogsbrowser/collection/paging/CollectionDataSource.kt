package work.beltran.discogsbrowser.collection.paging

import androidx.paging.PageKeyedDataSource
import work.beltran.discogsbrowser.collection.data.GetCollectionUseCase
import work.beltran.discogsbrowser.common.domain.Album

class CollectionDataSource(
    private val collectionUseCase: GetCollectionUseCase
) : PageKeyedDataSource<Int, Album>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {

        val result = collectionUseCase.getCollectionPage("0", 1)

        callback.onResult(
            result.data,
            0,
            result.totalItems,
            null,
            result.nextPage
        )

//        service.getCollectionItemsByFolder(
//            username = "mike513",
//            folderId = "0",
//            page = 1,
//            perPage = 20
//        ).enqueue(object : Callback<CollectionItemsByFolderResponse> {
//            override fun onFailure(call: Call<CollectionItemsByFolderResponse>, t: Throwable) {
//                error(t)
//            }
//
//            override fun onResponse(
//                call: Call<CollectionItemsByFolderResponse>,
//                response: Response<CollectionItemsByFolderResponse>
//            ) {
//
//                response.body()?.let { body ->
//                    val albums = body.releases.toAlbums()
//                    val nextPage = if (body.pagination.page < body.pagination.pages) body.pagination.page + 1 else null
//                    callback.onResult(
//                        albums, 0, body.pagination.items, null, nextPage
//                    )
//                }
//            }
//
//        })


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {

//        service.getCollectionItemsByFolder(
//            username = "mike513",
//            folderId = "0",
//            page = params.key,
//            perPage = 20
//        ).enqueue(object : Callback<CollectionItemsByFolderResponse> {
//            override fun onFailure(call: Call<CollectionItemsByFolderResponse>, t: Throwable) {
//                error(t)
//            }
//
//            override fun onResponse(
//                call: Call<CollectionItemsByFolderResponse>,
//                response: Response<CollectionItemsByFolderResponse>
//            ) {
//
//                response.body()?.let { body ->
//                    val albums = body.releases.toAlbums()
//                    val nextPage = if (body.pagination.page < body.pagination.pages) body.pagination.page + 1 else null
//                    callback.onResult(
//                        albums, nextPage
//                    )
//                }
//            }
//
//        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {

    }
}