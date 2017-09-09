package work.beltran.discogsbrowser.app.base

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

abstract class BaseViewModel<ViewModelData> {
    internal val disposables = CompositeDisposable()
    val data: BehaviorSubject<ViewModelData> = BehaviorSubject.create<ViewModelData>()

    fun clear() {
        Log.d("BaseViewModel", "clear ViewModel")
        disposables.clear()
    }
}

