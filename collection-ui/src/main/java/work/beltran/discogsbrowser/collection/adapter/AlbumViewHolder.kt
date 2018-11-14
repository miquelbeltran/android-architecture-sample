package work.beltran.discogsbrowser.collection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_album_item.view.*
import work.beltran.discogsbrowser.collection.CollectionViewHolder
import work.beltran.discogsbrowser.collection.R

class AlbumViewHolder(override val containerView: View): CollectionViewHolder(containerView), LayoutContainer {

    override fun bind(item: CollectionItem) {
        item as CollectionItem.AlbumItem
        itemView.nameTextView.text = item.album.title
        itemView.artistsTextView.text = item.album.artist.map { it.name }.all.joinToString()
    }

    companion object {
        fun create(parent: ViewGroup): AlbumViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_album_item, parent, false)
            return AlbumViewHolder(view)
        }
    }

}
