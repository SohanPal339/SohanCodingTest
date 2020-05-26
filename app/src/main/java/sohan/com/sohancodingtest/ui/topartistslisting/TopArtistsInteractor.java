package sohan.com.sohancodingtest.ui.topartistslisting;


import io.reactivex.Single;
import sohan.com.sohancodingtest.models.TopArtistsResponse;

public interface TopArtistsInteractor {
    Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey);
}
