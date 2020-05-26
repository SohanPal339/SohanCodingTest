package sohan.com.sohancodingtest.ui.topalbumslisting;


import io.reactivex.Single;
import sohan.com.sohancodingtest.models.TopAlbumsResponse;

public interface TopAlbumsInteractor {
    Single<TopAlbumsResponse> getTopAlbums(String userName, int limit, String apiKey);
}
