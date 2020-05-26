package sohan.com.sohancodingtest.ui.topartistslisting.di;


import javax.inject.Singleton;

import dagger.Component;
import sohan.com.sohancodingtest.ui.topartistslisting.TopArtistsFragment;

@Singleton
@Component(modules = {TopArtistsModule.class})
public interface TopArtistsComponent {
    void inject(TopArtistsFragment target);
}
