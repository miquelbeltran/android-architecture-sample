package work.beltran.discogsbrowser.collection.adapter

import androidx.recyclerview.widget.DiffUtil

class AlbumDiffCallback(
    private val oldList: List<CollectionItem>,
    private val newList: List<CollectionItem>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return when {
            oldItem is CollectionItem.LoadingItem && newItem is CollectionItem.LoadingItem -> true
            oldItem is CollectionItem.AlbumItem && newItem is CollectionItem.AlbumItem -> {
                oldItem.album.id == newItem.album.id
            }
            else -> false
        }
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}