package work.beltran.discogsbrowser.collection

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import work.beltran.discogsbrowser.api.provideService

val collectionKoinModule = module {
    single { provideService() }
    single { CollectionDataSourceFactory(get()) }
    viewModel { CollectionViewModel(get()) }
}

