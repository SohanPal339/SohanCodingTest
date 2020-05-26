package sohan.com.sohancodingtest.ui.toptrackslisting.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sohan.com.sohancodingtest.ui.toptrackslisting.TopTracksInteractor;
import sohan.com.sohancodingtest.ui.toptrackslisting.TopTracksInteractorImpl;
import sohan.com.sohancodingtest.ui.toptrackslisting.TopTracksPresenter;
import sohan.com.sohancodingtest.ui.toptrackslisting.TopTracksPresenterImpl;
import sohan.com.sohancodingtest.ui.toptrackslisting.TopTracksView;
import sohan.com.sohancodingtest.utils.Constants;


@Module
public class TopTracksModule {
    TopTracksView mView;

    public TopTracksModule(TopTracksView view) {
        mView = view;
    }

    // provides the view to create the top tracks presenter
    @Singleton
    @Provides
    public TopTracksView providesTopTracksView() {
        return this.mView;
    }

    // provides a converter factory to create the retrofit instance
    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory() {
        return GsonConverterFactory.create();
    }

    // provides a call adapter factory needed to integrate rxjava with retrofit
    @Singleton
    @Provides
    public CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    // provides a retrofit instance to create the top tracks interactor
    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory adapter) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converter)
                .build();
    }

    // provides top tracks interactor to make an instance of the presenter
    @Singleton
    @Provides
    public TopTracksInteractor providesTopTopTracksInteractor(Retrofit retrofit) {
        return new TopTracksInteractorImpl(retrofit);
    }

    // provides top albums presenter
    @Singleton
    @Provides
    public TopTracksPresenter providesTopTracksPresenter(TopTracksView view, TopTracksInteractor interactor) {
        return new TopTracksPresenterImpl(view, interactor);

    }
}
