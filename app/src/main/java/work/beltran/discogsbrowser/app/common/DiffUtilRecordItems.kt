package work.beltran.discogsbrowser.app.common

import android.support.v7.util.DiffUtil

class DiffUtilRecordItems(private val newList: List<RecordListItem>,
                          private val oldList: List<RecordListItem>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].releaseId == newList[newItemPosition].releaseId

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

}