package work.beltran.discogsbrowser.collection.paging

import androidx.paging.DataSource
import work.beltran.discogsbrowser.collection.data.GetCollectionUseCase
import work.beltran.discogsbrowser.common.domain.Album

class CollectionDataSourceFactory(
    private val getCollectionUseCase: GetCollectionUseCase
) : DataSource.Factory<Int, Album>() {
    override fun create(): DataSource<Int, Album> {
        return CollectionDataSource(getCollectionUseCase)
    }
}