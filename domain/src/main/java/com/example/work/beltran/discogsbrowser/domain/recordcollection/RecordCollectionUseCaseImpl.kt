package com.example.work.beltran.discogsbrowser.domain.recordcollection

import com.example.work.beltran.discogsbrowser.domain.UseCase
import io.reactivex.Completable
import io.reactivex.Flowable
import work.beltran.discogsbrowser.business.CollectionRepository
import work.beltran.discogsbrowser.business.model.Record

class RecordCollectionUseCaseImpl(private val collectionRepository: CollectionRepository)
    : UseCase<List<Record>, RecordCollectionUseCaseParams>(), RecordCollectionUseCase {

    override fun fetch(params: RecordCollectionUseCaseParams): Completable =
            collectionRepository.fetchRecords(params.page)

    override fun getStream(): Flowable<List<Record>> =
            collectionRepository.getAllRecords()
}