package sohan.com.sohancodingtest.ui.topalbumslisting;


import io.reactivex.Single;
import retrofit2.Retrofit;
import sohan.com.sohancodingtest.models.TopAlbumsResponse;
import sohan.com.sohancodingtest.network.TopAlbumsService;


public class TopAlbumsInteractorImpl implements TopAlbumsInteractor {
    Retrofit mRetrofit;

    public TopAlbumsInteractorImpl(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Single<TopAlbumsResponse> getTopAlbums(String userName, int limit, String apiKey) {
        return mRetrofit.create(TopAlbumsService.class).getTopArtists(userName, limit, apiKey);
    }
}
