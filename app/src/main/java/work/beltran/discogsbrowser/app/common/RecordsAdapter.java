package work.beltran.discogsbrowser.app.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;
import work.beltran.discogsbrowser.api.model.record.Record;

/**
 * Created by Miquel Beltran on 8/27/16
 * More on http://beltran.work
 */
public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordViewHolder> {

    private List<Record> records = new ArrayList<>();
    private Picasso picasso;

    public RecordsAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public RecordsAdapter.RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_record, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordsAdapter.RecordViewHolder holder, int position) {
        Record record = records.get(position);
        holder.textTitle.setText(record.getBasicInformation().getTitle());
        holder.textArtist.setText(record.getBasicInformation().getArtists().get(0).getName());
        holder.textFormat.setText(record.getBasicInformation().getFormats().get(0).getName());
        holder.textYear.setText(record.getBasicInformation().getYear());
        picasso.load(record.getBasicInformation().getThumb())
                .fit()
                .centerCrop()
                .into(holder.iamgeThumb);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void addItems(List<Record> records) {
        int positionStart = this.records.size();
        this.records.addAll(records);
        notifyItemRangeInserted(positionStart, records.size());
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.image_thumb)
        ImageView iamgeThumb;
        @BindView(R.id.text_artist)
        TextView textArtist;
        @BindView(R.id.text_year)
        TextView textYear;
        @BindView(R.id.text_format)
        TextView textFormat;

        public RecordViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
