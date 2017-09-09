package work.beltran.discogsbrowser.app.common

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import work.beltran.discogsbrowser.R
import java.util.*



/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
class RecordsAdapter(private val picasso: Picasso) : RecyclerView.Adapter<RecordViewHolder>() {
    private val records = ArrayList<RecordListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_record, parent, false)
        return RecordViewHolder(view, picasso)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(records[position])
    }

    override fun getItemCount(): Int = records.size

    fun addItems(records: List<RecordListItem>) {
        val positionStart = this.records.size
        this.records.addAll(records)
        notifyItemRangeInserted(positionStart, records.size)
    }

    fun clear() {
        records.clear()
        notifyDataSetChanged()
    }

    fun getItems(): List<RecordListItem> = records

    override fun getItemId(position: Int): Long = records[position].releaseId.toLong()

    fun removeItem(releaseId: Int) {
        records.forEachIndexed { index, recordListItem ->
            if (recordListItem.releaseId == releaseId) {
                records.removeAt(index)
                notifyItemRemoved(index)
                return
            }
        }
    }

    fun updateItems(collection: List<RecordListItem>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilRecordItems(records, collection))
        records.clear()
        records.addAll(collection)
        diffResult.dispatchUpdatesTo(this)
    }
}

