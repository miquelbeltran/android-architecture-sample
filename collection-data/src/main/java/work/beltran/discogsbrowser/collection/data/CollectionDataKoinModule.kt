package work.beltran.discogsbrowser.collection.data

import androidx.paging.DataSource
import org.koin.dsl.module.module
import work.beltran.discogsbrowser.api.provideService
import work.beltran.discogsbrowser.common.domain.Album

val collectionDataKoinModule = module {
    single { provideService() }
    single { CollectionDataSourceFactory(get()) as DataSource.Factory<Int, Album> }
}

