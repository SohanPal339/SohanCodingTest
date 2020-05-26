package sohan.com.sohancodingtest.ui.toptrackslisting;


import io.reactivex.Single;
import retrofit2.Retrofit;
import sohan.com.sohancodingtest.models.TopTracksResponse;
import sohan.com.sohancodingtest.network.TopTracksService;


public class TopTracksInteractorImpl implements TopTracksInteractor {
    Retrofit mRetrofit;

    public TopTracksInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopTracksResponse> getTopTracks(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopTracksService.class).getTopTracks(userName, limit, apiKey);
    }
}
