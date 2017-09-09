package work.beltran.discogsbrowser.app.common

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.card_record.view.*
import work.beltran.discogsbrowser.R
import work.beltran.discogsbrowser.app.release.ReleaseActivity

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
class RecordViewHolder(itemView: View,
                       private val picasso: Picasso)
    : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val textTitle: TextView by lazy { itemView.text_title }
    private var record: RecordListItem? = null

    init {
        itemView.setOnClickListener(this)
    }

    internal fun bind(record: RecordListItem) {
        textTitle.text = record.title
        this.record = record
    }

    override fun onClick(v: View) {
        v.context.startActivity(ReleaseActivity.createReleaseActivity(v.context, record))
    }
}
