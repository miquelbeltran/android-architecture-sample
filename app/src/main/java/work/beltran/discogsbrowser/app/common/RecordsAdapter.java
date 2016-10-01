package work.beltran.discogsbrowser.app.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import work.beltran.discogsbrowser.R;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class RecordsAdapter extends RecyclerView.Adapter<RecordViewHolder> {

    private final Picasso picasso;
    private List<RecordAdapterItem> records = new ArrayList<>();

    public RecordsAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_record, parent, false);
        return new RecordViewHolder(view, picasso);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        holder.bind(records.get(position));
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void addItems(List<RecordAdapterItem> records) {
        int positionStart = this.records.size();
        this.records.addAll(records);
        notifyItemRangeInserted(positionStart, records.size());
    }

    public void clear() {
        records.clear();
        notifyDataSetChanged();
    }

}
