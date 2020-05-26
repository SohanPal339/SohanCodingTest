package sohan.com.sohancodingtest.ui.topalbumslisting;

import java.util.List;

import sohan.com.sohancodingtest.models.Album;

public interface TopAlbumsView {
    void showProgress();

    void hideProgress();

    void showError();

    void updateData(List<Album> topAlbums);

    void showEmpty();

    void hidEmpty();
}
