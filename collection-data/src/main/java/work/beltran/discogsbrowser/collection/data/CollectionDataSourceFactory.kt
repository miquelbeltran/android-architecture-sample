package work.beltran.discogsbrowser.collection.data

import androidx.paging.DataSource
import work.beltran.discogsbrowser.api.DiscogsService
import work.beltran.discogsbrowser.common.domain.Album

class CollectionDataSourceFactory(
    private val service: DiscogsService
) : DataSource.Factory<Int, Album>() {
    override fun create(): DataSource<Int, Album> {
        return CollectionDataSource(service)
    }
}