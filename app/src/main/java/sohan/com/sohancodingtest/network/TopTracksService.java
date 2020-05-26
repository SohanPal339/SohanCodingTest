package sohan.com.sohancodingtest.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sohan.com.sohancodingtest.models.TopTracksResponse;


public interface TopTracksService {
    @GET("?method=user.gettoptracks&format=json")
    Single<TopTracksResponse> getTopTracks(@Query("user") String user, @Query("limit") int limit, @Query("api_key") String apiKey);

}
