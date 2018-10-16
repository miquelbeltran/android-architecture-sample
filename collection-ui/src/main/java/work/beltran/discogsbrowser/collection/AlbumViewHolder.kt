package work.beltran.discogsbrowser.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_album_item.view.*
import work.beltran.discogsbrowser.common.domain.Album

class AlbumViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(album: Album) {
        itemView.nameTextView.text = album.title
    }

    companion object {
        fun create(parent: ViewGroup): AlbumViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_album_item, parent, false)
            return AlbumViewHolder(view)
        }
    }

}
