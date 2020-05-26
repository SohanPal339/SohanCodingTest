package sohan.com.sohancodingtest.ui.topartistslisting;


public interface TopArtistsPresenter {
    void onDestroy();

    void getUserTopArtists(String userName, int limit, String apiKey);
}
