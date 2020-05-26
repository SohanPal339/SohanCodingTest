package sohan.com.sohancodingtest.ui.topartistslisting;


import io.reactivex.Single;
import retrofit2.Retrofit;
import sohan.com.sohancodingtest.models.TopArtistsResponse;
import sohan.com.sohancodingtest.network.TopArtistsService;

public class TopArtistsInteractorImpl implements TopArtistsInteractor {
    Retrofit mRetrofit;

    public TopArtistsInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopArtistsResponse> getTopArtists(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopArtistsService.class).getTopArtists(userName, limit, apiKey);
    }
}
