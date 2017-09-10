package com.example.work.beltran.discogsbrowser.domain.recordcollection

data class RecordCollectionUseCaseParams(
        val page: Int = 0,
        val flush: Boolean = false
)