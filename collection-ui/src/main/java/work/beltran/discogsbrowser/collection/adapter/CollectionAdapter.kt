package work.beltran.discogsbrowser.collection.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import work.beltran.discogsbrowser.collection.CollectionViewHolder
import work.beltran.discogsbrowser.common.domain.Album

class CollectionAdapter : RecyclerView.Adapter<CollectionViewHolder>() {

    private var items = emptyList<CollectionItem>()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is CollectionItem.AlbumItem -> TYPE_ALBUM
            is CollectionItem.LoadingItem -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return when (viewType) {
            TYPE_LOADING -> LoadingViewHolder.create(parent)
            TYPE_ALBUM -> AlbumViewHolder.create(parent)
            else -> error("Unknown ViewHolder type $viewType")
        }
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(albums: List<CollectionItem>) {
        val oldList = items
        items = albums
        val diffResult = DiffUtil.calculateDiff(
            AlbumDiffCallback(
                oldList,
                items
            )
        )
        diffResult.dispatchUpdatesTo(this)
    }
}

const val TYPE_ALBUM = 0
const val TYPE_LOADING = 1

sealed class CollectionItem {
    class AlbumItem(val album: Album) : CollectionItem()
    object LoadingItem : CollectionItem()
}

