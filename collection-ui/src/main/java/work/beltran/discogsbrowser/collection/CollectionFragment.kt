package work.beltran.discogsbrowser.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_collection.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import work.beltran.discogsbrowser.common.domain.Album

private const val THRESHOLD = 5

class CollectionFragment : Fragment() {

    private val viewModel: CollectionViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_collection, container, false)
        val adapter = CollectionAdapter()
        configureRecyclerView(view, adapter)
        viewModel.liveData.observe(viewLifecycleOwner, Observer<List<Album>> { adapter.submitList(it) })
        return view
    }

    private fun configureRecyclerView(
        view: View,
        adapter: CollectionAdapter
    ) {
        view.recycler.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(view.context)
        view.recycler.layoutManager = linearLayoutManager
        createScrollListener(view, linearLayoutManager)
    }

    private fun createScrollListener(
        view: View,
        linearLayoutManager: LinearLayoutManager
    ) {
        view.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = linearLayoutManager.itemCount
                val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if (totalItemCount <= lastVisibleItem + THRESHOLD) {
                    viewModel.loadMore()
                }
            }
        })
    }
}