package work.beltran.discogsbrowser.app.collection

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.reactivex.disposables.Disposable
import work.beltran.discogsbrowser.R
import work.beltran.discogsbrowser.app.App

class CollectionController : Controller() {

    private val viewModel: CollectionViewModel by lazy {
        Log.d(TAG, "ViewModel injected")
        (activity?.application as App).apiComponent.collectionViewModel()
    }

    private lateinit var dataDisposable: Disposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_collection, container, false)
        dataDisposable = viewModel.data.subscribe({
            Log.d(TAG, "Got Screen Data, size: ${it.collection.size}")
        })
        return view
    }

    override fun onDestroyView(view: View) {
        dataDisposable.dispose()
        super.onDestroyView(view)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        viewModel.clear()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "CollectionController"
    }
}

