package work.beltran.discogsbrowser.app.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.beltran.discogsbrowser.R;

/**
 * Created by Miquel Beltran on 10/1/16
 * More on http://beltran.work
 */
public class RecordViewHolder extends RecyclerView.ViewHolder {
    private Picasso picasso;
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

    public RecordViewHolder(View itemView, Picasso picasso) {
        super(itemView);
        this.picasso = picasso;
        ButterKnife.bind(this, itemView);
    }

    public void bind(RecordAdapterItem record) {
        textTitle.setText(record.getTitle());
        textArtist.setText(record.getArtist());
        textFormat.setText(record.getFormat());
        textYear.setText(record.getYear());
        picasso.load(record.getThumb())
                .fit()
                .centerCrop()
                .into(iamgeThumb);
    }
}
