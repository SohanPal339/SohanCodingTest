package sohan.com.sohancodingtest.ui.topartistslisting;


import java.util.List;

import sohan.com.sohancodingtest.models.Artist;


public interface TopArtistsView {
    void showProgress();

    void hideProgress();

    void updateData(List<Artist> topArtists);

    void showError();
    void showEmpty();
    void hidEmpty();

}
