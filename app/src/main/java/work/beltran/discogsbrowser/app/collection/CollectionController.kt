package work.beltran.discogsbrowser.app.collection

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.view_collection.view.*
import work.beltran.discogsbrowser.R
import work.beltran.discogsbrowser.app.App
import work.beltran.discogsbrowser.app.common.RecordsAdapter

class CollectionController : Controller() {

    private val viewModel: CollectionViewModel by lazy {
        Log.d(TAG, "ViewModel injected")
        app().apiComponent.collectionViewModel()
    }
    private val recordsAdapter: RecordsAdapter by lazy { RecordsAdapter() }

    private lateinit var dataDisposable: Disposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_collection, container, false)
        with(view.recycler_records) {
            adapter = recordsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        dataDisposable = viewModel.data
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, "Got Screen Data, size: ${it.collection.size}")
                    recordsAdapter.updateItems(it.collection)
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

    private fun app() = (activity?.application as App)

    companion object {
        private const val TAG = "CollectionController"
    }
}

