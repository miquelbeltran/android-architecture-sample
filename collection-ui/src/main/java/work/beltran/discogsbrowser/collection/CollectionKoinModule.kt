package work.beltran.discogsbrowser.collection

import androidx.paging.DataSource
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import work.beltran.discogsbrowser.collection.data.GetCollectionUseCase
import work.beltran.discogsbrowser.collection.data.provideGetCollectionUseCase
import work.beltran.discogsbrowser.collection.paging.CollectionDataSourceFactory
import work.beltran.discogsbrowser.common.domain.Album
import kotlin.coroutines.experimental.CoroutineContext

val collectionKoinModule = module {
    single { provideGetCollectionUseCase() }
    single { CollectionDataSourceFactory(get()) as DataSource.Factory<Int, Album> }
    viewModel { CollectionViewModel(get()) }
}

