package work.beltran.discogsbrowser

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import work.beltran.discogsbrowser.collection.CollectionViewModel
import work.beltran.discogsbrowser.collection.data.provideGetCollectionUseCase

val collectionKoinModule = module {
    single { provideGetCollectionUseCase() }
    viewModel { CollectionViewModel(get()) }
}
