package sohan.com.sohancodingtest.ui.toptrackslisting;


import io.reactivex.Single;
import sohan.com.sohancodingtest.models.TopTracksResponse;


public interface TopTracksInteractor {
    Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey);

}
