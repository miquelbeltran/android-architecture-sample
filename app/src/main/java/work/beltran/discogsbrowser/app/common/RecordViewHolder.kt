package work.beltran.discogsbrowser.app.common

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.card_record.view.*
import work.beltran.discogsbrowser.R
import work.beltran.discogsbrowser.app.release.ReleaseActivity

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
class RecordViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val textTitle: TextView by lazy { itemView.text_title }
    private val thumbImage: ImageView by lazy { itemView.image_thumb }
    private var record: RecordItem? = null

    init {
        itemView.setOnClickListener(this)
    }

    internal fun bind(record: RecordItem) {
        textTitle.text = record.title
        showAlbumArt(record)
        this.record = record
    }

    private fun showAlbumArt(record: RecordItem) {
        if (record.thumb.isEmpty()) {
            thumbImage.setImageResource(R.drawable.music_record)
        } else {
            Picasso.with(thumbImage.context)
                    .load(record.thumb)
                    .placeholder(R.drawable.music_record)
                    .centerCrop()
                    .fit()
                    .into(thumbImage)
        }
    }

    override fun onClick(v: View) {
        v.context.startActivity(ReleaseActivity.createReleaseActivity(v.context, record))
    }
}
