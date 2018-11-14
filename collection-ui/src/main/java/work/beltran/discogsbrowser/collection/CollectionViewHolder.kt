package work.beltran.discogsbrowser.collection

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import work.beltran.discogsbrowser.collection.adapter.CollectionItem

abstract class CollectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: CollectionItem)
}