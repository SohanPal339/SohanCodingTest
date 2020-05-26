package sohan.com.sohancodingtest.ui.topalbumslisting.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sohan.com.sohancodingtest.ui.topalbumslisting.TopAlbumsInteractor;
import sohan.com.sohancodingtest.ui.topalbumslisting.TopAlbumsInteractorImpl;
import sohan.com.sohancodingtest.ui.topalbumslisting.TopAlbumsPresenter;
import sohan.com.sohancodingtest.ui.topalbumslisting.TopAlbumsPresenterImpl;
import sohan.com.sohancodingtest.ui.topalbumslisting.TopAlbumsView;
import sohan.com.sohancodingtest.utils.Constants;

@Module
public class TopAlbumsModule {
    TopAlbumsView mView;

    public TopAlbumsModule(TopAlbumsView view) {
        mView = view;
    }

    // provides the view to create the top albums presenter
    @Singleton
    @Provides
    public TopAlbumsView providesTopAlbumsView() {
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

    // provides a retrofit instance to create the top albums interactor
    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory adapter) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(adapter)
                .addConverterFactory(converter)
                .build();
    }

    // provides top albums interactor to make an instance of the presenter
    @Singleton
    @Provides
    public TopAlbumsInteractor providesTopAlbumsInteractor(Retrofit retrofit) {
        return new TopAlbumsInteractorImpl(retrofit);
    }

    // provides top albums presenter
    @Singleton
    @Provides
    public TopAlbumsPresenter providesTopAlbumsPresenter(TopAlbumsView view, TopAlbumsInteractor interactor) {
        return new TopAlbumsPresenterImpl(view, interactor);

    }

}
