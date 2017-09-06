package work.beltran.discogsbrowser.business

import io.reactivex.Flowable

class MemoryReactiveStoreImpl<in Key, Value> : ReactiveStore<Key, Value> {
    override fun getAll(): Flowable<List<Value>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun replace(list: List<Value>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(value: Value) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: Key): Flowable<Value> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}