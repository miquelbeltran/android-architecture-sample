package work.beltran.discogsbrowser.app.collection

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import work.beltran.discogsbrowser.R
import work.beltran.discogsbrowser.app.App

class CollectionController : Controller() {

    private val viewModel: CollectionViewModel by lazy {
        (activity?.application as App).apiComponent.collectionViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_collection, container, false)
        viewModel.screenData(Consumer {
            Log.d(TAG, it.toString())
        })
        return view
    }

    override fun onDestroyView(view: View) {
        viewModel.dispose()
        super.onDestroyView(view)
    }

    companion object {
        private const val TAG = "CollectionController"
    }
}

