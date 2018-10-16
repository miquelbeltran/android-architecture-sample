package work.beltran.discogsbrowser.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_collection.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import work.beltran.discogsbrowser.common.domain.Album

class CollectionFragment : Fragment() {

    private val viewModel: CollectionViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val adapter = CollectionAdapter()

        viewModel.liveData.observe(viewLifecycleOwner, Observer<PagedList<Album>> { adapter.submitList(it) })

        val view = inflater.inflate(R.layout.fragment_collection, container, false)

        view.recycler.adapter = adapter
        view.recycler.layoutManager = LinearLayoutManager(view.context)

        return view
    }
}