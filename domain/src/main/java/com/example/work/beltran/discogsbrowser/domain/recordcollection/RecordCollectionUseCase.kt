package com.example.work.beltran.discogsbrowser.domain.recordcollection

import io.reactivex.Completable
import io.reactivex.Flowable
import work.beltran.discogsbrowser.business.model.Record

interface RecordCollectionUseCase {
    fun fetch(params: RecordCollectionUseCaseParams): Completable
    fun getStream(): Flowable<List<Record>>
}

