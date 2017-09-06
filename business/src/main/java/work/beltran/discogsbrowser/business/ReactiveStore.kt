package work.beltran.discogsbrowser.business

import io.reactivex.Flowable

interface ReactiveStore<in Key, Value> {
    fun getAll(): Flowable<List<Value>>
    fun replace(list: List<Value>)
    fun remove(value: Value)
    fun get(id: Key): Flowable<Value>
}