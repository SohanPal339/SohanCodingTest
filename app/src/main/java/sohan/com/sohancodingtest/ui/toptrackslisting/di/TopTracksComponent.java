package sohan.com.sohancodingtest.ui.toptrackslisting.di;


import javax.inject.Singleton;

import dagger.Component;
import sohan.com.sohancodingtest.ui.toptrackslisting.TopTracksFragment;


@Singleton
@Component(modules = {TopTracksModule.class})
public interface TopTracksComponent {
    void inject(TopTracksFragment target);
}
