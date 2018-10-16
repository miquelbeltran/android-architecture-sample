package work.beltran.discogsbrowser.collection.data

import work.beltran.discogsbrowser.common.domain.Album
import work.beltran.discogsbrowser.common.domain.Pagination

class GetCollectionUseCase {

    fun getCollectionPage(
        folder: String,
        page: Int
    ): Pagination<List<Album>> {
        return Pagination(emptyList(), 1, null, 0)
    }
}

