package sohan.com.sohancodingtest.ui.toptrackslisting;

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
import sohan.com.sohancodingtest.adapters.TopTracksAdapter;
import sohan.com.sohancodingtest.models.Track;
import sohan.com.sohancodingtest.ui.BaseFragment;
import sohan.com.sohancodingtest.ui.toptrackslisting.di.DaggerTopTracksComponent;
import sohan.com.sohancodingtest.ui.toptrackslisting.di.TopTracksModule;
import sohan.com.sohancodingtest.utils.Constants;

/**
 * A simple Fragment that displays top tracks
 */
public class TopTracksFragment extends BaseFragment implements TopTracksView {
    private OnFragmentInteractionListener mListener;
    @BindView(R.id.rclr_tracks)
    RecyclerView tracksRecyclerView;
    @BindView(R.id.prgrs_main)
    ProgressBar mainProgressBar;
    @BindView(R.id.empty_layout)
    View emptyLayout;
    @Inject
    TopTracksPresenter mPresenter;
    TopTracksAdapter mAdapter;

    public TopTracksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static TopTracksFragment newInstance() {
        TopTracksFragment fragment = new TopTracksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTopTracksComponent.builder().topTracksModule(new TopTracksModule(this)).build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getTopTracks(Constants.DEFAULT_LASTFM_USER, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_tracks, container, false);
        ButterKnife.bind(this, view);
        return view;
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
    public void showProgress() {
        mainProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mainProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.general_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateData(List<Track> tracks) {
        if (mAdapter == null) {
            mAdapter = new TopTracksAdapter(tracks, getContext(), onTrackClickedListener);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            tracksRecyclerView.setLayoutManager(linearLayoutManager);
            tracksRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.setDataset(tracks);
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

    @Override
    protected void searchUserName(String userName) {
        if (mAdapter != null) {
            mAdapter.clearDataset();
        }
        mPresenter.getTopTracks(userName, Constants.TOP_ITEMS_LIMIT, Constants.API_KEY);

    }

    public interface OnFragmentInteractionListener {
        void onTrackClicked(Track track);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    View.OnClickListener onTrackClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = tracksRecyclerView.getChildAdapterPosition(view);
                Track track = mAdapter.getItemAt(position);
                mListener.onTrackClicked(track);
            }
        }
    };
}
