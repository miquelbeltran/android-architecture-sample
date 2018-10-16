package work.beltran.discogsbrowser.collection

import androidx.paging.DataSource
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import work.beltran.discogsbrowser.collection.data.GetCollectionUseCase
import work.beltran.discogsbrowser.collection.paging.CollectionDataSourceFactory
import work.beltran.discogsbrowser.common.domain.Album

val collectionKoinModule = module {
    single { GetCollectionUseCase() }
    single { CollectionDataSourceFactory(get()) as DataSource.Factory<Int, Album> }
    viewModel { CollectionViewModel(get()) }
}

