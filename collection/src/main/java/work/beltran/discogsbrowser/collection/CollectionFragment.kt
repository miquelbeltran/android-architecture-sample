package work.beltran.discogsbrowser.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

class CollectionFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val adapter = CollectionAdapter()
        val viewModel = CollectionViewModel()

        viewModel.liveData.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

        val view = inflater.inflate(R.layout.fragment_collection, container, false)

        return view
    }
}