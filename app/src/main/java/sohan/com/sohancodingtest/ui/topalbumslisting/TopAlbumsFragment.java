package sohan.com.sohancodingtest.ui.topalbumslisting;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import sohan.com.sohancodingtest.R;
import sohan.com.sohancodingtest.adapters.TopAlbumsAdapter;
import sohan.com.sohancodingtest.models.Album;
import sohan.com.sohancodingtest.ui.BaseFragment;
import sohan.com.sohancodingtest.ui.topalbumslisting.di.DaggerTopAlbumsComponent;
import sohan.com.sohancodingtest.ui.topalbumslisting.di.TopAlbumsModule;
import sohan.com.sohancodingtest.utils.Constants;

/**
 * A simple Fragment that displays top albums for user
 */
public class TopAlbumsFragment extends BaseFragment implements TopAlbumsView {
    @BindView(R.id.rclr_albums)
    RecyclerView albumsRecyclerView;
    @BindView(R.id.prgrs_main)
    ProgressBar mainprogressBar;
    @BindView(R.id.empty_layout)
    View emptyLayout;
    @Inject
    TopAlbumsPresenter mPresenter;
    TopAlbumsAdapter mAdapter;
    private OnFragmentInteractionListener mListener;

    public TopAlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_albums, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTopAlbumsComponent.builder().topAlbumsModule(new TopAlbumsModule(this)).build().inject(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getTopAlbums(Constants.DEFAULT_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected void searchUserName(String userName) {
        if (mAdapter != null) {
            mAdapter.clearDataset();
        }
        mPresenter.getTopAlbums(userName, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);

    }

    @Override
    public void showProgress() {
        mainprogressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mainprogressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateData(List<Album> topAlbums) {
        if (mAdapter == null) {

            mAdapter = new TopAlbumsAdapter(topAlbums, getContext(), mOnAlbumClickedListener);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            albumsRecyclerView.setLayoutManager(layoutManager);
            albumsRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.setDataset(topAlbums);
        }


    }

    @Override
    public void showEmpty() {
        emptyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidEmpty() {
        emptyLayout.setVisibility(View.GONE);
    }

    View.OnClickListener mOnAlbumClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = albumsRecyclerView.getChildAdapterPosition(view);
                mListener.onAlbumClicked(mAdapter.getItemByPosition(position));
            }
        }
    };

    public static TopAlbumsFragment newInstance() {
        return new TopAlbumsFragment();
    }


    public interface OnFragmentInteractionListener {

        void onAlbumClicked(Album album);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
