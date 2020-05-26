package sohan.com.sohancodingtest.ui.toptrackslisting;


import java.util.List;

import sohan.com.sohancodingtest.models.Track;


public interface TopTracksView {
    void showProgress();

    void hideProgress();

    void showError();

    void updateData(List<Track> tracks);
    void showEmpty();
    void hidEmpty();
}
