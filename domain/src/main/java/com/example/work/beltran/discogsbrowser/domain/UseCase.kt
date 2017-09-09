package com.example.work.beltran.discogsbrowser.domain

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.observers.DisposableObserver
import org.reactivestreams.Subscriber
import work.beltran.discogsbrowser.business.model.Record

abstract class UseCase<Type, Params> {
    abstract fun getStream(): Flowable<Type>
    abstract fun fetch(params: Params): Completable
}