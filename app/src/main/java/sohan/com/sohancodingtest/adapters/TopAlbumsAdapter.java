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
import sohan.com.sohancodingtest.models.Album;
import sohan.com.sohancodingtest.utils.ImageLoader;

public class TopAlbumsAdapter extends RecyclerView.Adapter<TopAlbumsAdapter.ViewHolder> {
    List<Album> mDataset;
    Context mContext;
    View.OnClickListener mOnItemClickListener;

    public TopAlbumsAdapter(List<Album> items, Context context, View.OnClickListener onClickListener) {
        this.mDataset = items;
        this.mContext = context;
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album item = mDataset.get(position);
        ImageLoader.loadImage(mContext, item.getImageUrl(), R.drawable.default_album, holder.albumImageView);
        holder.nameTextView.setText(item.getName());
        holder.playCountTextView.setText(item.getPlaycount());
        holder.artistTextView.setText(item.getArtist().getName());
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        }
        return 0;
    }

    public void setDataset(List<Album> items) {
        this.mDataset = items;
        notifyDataSetChanged();
    }

    public Album getItemByPosition(int position) {
        return mDataset.get(position);
    }

    public void clearDataset() {
        if (mDataset != null) {
            mDataset.clear();
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_album)
        ImageView albumImageView;
        @BindView(R.id.txt_album_artist)
        TextView artistTextView;
        @BindView(R.id.txt_album_name)
        TextView nameTextView;
        @BindView(R.id.txt_plays)
        TextView playCountTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cv_album_item)
        void onItemClicked(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(view);
            }
        }
    }
}
