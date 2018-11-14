package work.beltran.discogsbrowser.collection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import work.beltran.discogsbrowser.collection.CollectionViewHolder
import work.beltran.discogsbrowser.collection.R

class LoadingViewHolder(itemView: View): CollectionViewHolder(itemView) {
    override fun bind(item: CollectionItem) {

    }

    companion object {
        fun create(parent: ViewGroup): LoadingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_loading_item, parent, false)
            return LoadingViewHolder(view)
        }
    }
}