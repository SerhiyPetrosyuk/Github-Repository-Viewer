package com.mlsdev.serhiy.githubviewer.internal.di.components;

import com.mlsdev.serhiy.githubviewer.App;
import com.mlsdev.serhiy.githubviewer.internal.di.modules.AppModule;
import com.mlsdev.serhiy.githubviewer.internal.di.modules.FollowModule;
import com.mlsdev.serhiy.githubviewer.internal.di.modules.NetModule;
import com.mlsdev.serhiy.githubviewer.internal.di.modules.PresenterModule;
import com.mlsdev.serhiy.githubviewer.internal.di.modules.RepositoryModule;
import com.mlsdev.serhiy.githubviewer.internal.di.modules.UserModule;
import com.mlsdev.serhiy.githubviewer.view.fragment.DetailFragment;
import com.mlsdev.serhiy.githubviewer.view.fragment.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */

@Singleton
@Component(modules = {AppModule.class, FollowModule.class, RepositoryModule.class, UserModule.class,
        PresenterModule.class, NetModule.class})
public interface AppComponent {

    void inject(SearchFragment searchFragment);
    void inject(DetailFragment detailFragment);
    void inject(App app);

}
