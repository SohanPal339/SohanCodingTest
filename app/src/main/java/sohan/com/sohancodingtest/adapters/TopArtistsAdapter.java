package sohan.com.sohancodingtest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sohan.com.sohancodingtest.R;
import sohan.com.sohancodingtest.models.Artist;
import sohan.com.sohancodingtest.utils.ImageLoader;

public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.ViewHolder> {
    private List<Artist> mDataset;
    private View.OnClickListener mOnClickListener;
    private Context mContext;

    public TopArtistsAdapter(List<Artist> items, Context context, View.OnClickListener onItemClickListener) {
        this.mDataset = items;
        this.mOnClickListener = onItemClickListener;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist item = mDataset.get(position);
        ImageLoader.loadImage(mContext, item.getImageUrl(), R.drawable.default_artist, holder.artistImageView);
        holder.artistTextView.setText(item.getName());
        holder.numberOfPlaysTextView.setText(item.getPlaycount());
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public void setDataset(List<Artist> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }

    public Artist getItemByPosition(int position) {
        return mDataset.get(position);
    }

    public void clearDataset() {
        if(mDataset!=null){
            mDataset.clear();
            notifyDataSetChanged();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_artist)
        ImageView artistImageView;
        @BindView(R.id.txt_artist_name)
        TextView artistTextView;
        @BindView(R.id.txt_plays)
        TextView numberOfPlaysTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cv_artist_item)
        void onItemClicked(View view) {
            if (mOnClickListener != null) {
                mOnClickListener.onClick(view);
            }
        }
    }

}
