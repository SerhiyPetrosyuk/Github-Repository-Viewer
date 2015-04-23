package com.mlsdev.serhiy.githubviewer.internal.di.modules;

import android.content.Context;

import com.mlsdev.serhiy.githubviewer.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */

@Module
public class AppModule {

    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides @Singleton
    Context provideContext(){
        return mApp.getApplicationContext();
    }
}
