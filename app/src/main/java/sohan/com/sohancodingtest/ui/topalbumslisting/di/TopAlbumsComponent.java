package sohan.com.sohancodingtest.ui.topalbumslisting.di;


import javax.inject.Singleton;

import dagger.Component;
import sohan.com.sohancodingtest.ui.topalbumslisting.TopAlbumsFragment;

@Singleton
@Component(modules = {TopAlbumsModule.class})
public interface TopAlbumsComponent {
    void inject(TopAlbumsFragment target);
}
