package com.mlsdev.serhiy.githubviewer.internal.di.modules;

import com.mlsdev.serhiy.githubviewer.presenter.DetailPresenter;
import com.mlsdev.serhiy.githubviewer.presenter.DetailUserInfoPresenter;
import com.mlsdev.serhiy.githubviewer.presenter.SearchPresenter;
import com.mlsdev.serhiy.githubviewer.presenter.SearchUserPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Module
public class PresenterModule {

    @Provides @Singleton
    SearchPresenter provideSearchUserPresenter(SearchUserPresenter presenter){
        return presenter;
    }

    @Provides @Singleton
    DetailPresenter provideDetailUserPresenter(DetailUserInfoPresenter presenter){
        return presenter;
    }

}
