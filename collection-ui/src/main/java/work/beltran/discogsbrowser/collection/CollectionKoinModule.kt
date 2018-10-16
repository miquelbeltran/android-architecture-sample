package work.beltran.discogsbrowser.collection

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val collectionKoinModule = module {
    viewModel { CollectionViewModel(get()) }
}

