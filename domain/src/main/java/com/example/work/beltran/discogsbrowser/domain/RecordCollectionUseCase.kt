package com.example.work.beltran.discogsbrowser.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import work.beltran.discogsbrowser.business.CollectionRepository
import work.beltran.discogsbrowser.business.model.Record

class RecordCollectionUseCase(private val collectionRepository: CollectionRepository)
    : UseCase<List<Record>, RecordCollectionUseCaseParams>() {

    override fun fetch(params: RecordCollectionUseCaseParams): Completable =
            collectionRepository.fetchRecords(params.page)

    override fun getStream(): Flowable<List<Record>> =
            collectionRepository.getAllRecords()
}

data class RecordCollectionUseCaseParams(
        val page: Int = 0,
        val flush: Boolean = false
)

