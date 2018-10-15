package work.beltran.discogsbrowser.collection

import androidx.paging.DataSource
import work.beltran.discogsbrowser.api.DiscogsService

class CollectionDataSourceFactory(
    private val service: DiscogsService
) : DataSource.Factory<Int, Album>() {
    override fun create(): DataSource<Int, Album> {
        return CollectionDataSource(service)
    }
}