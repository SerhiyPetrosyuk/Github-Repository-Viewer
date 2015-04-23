package com.mlsdev.serhiy.githubviewer.internal.di.modules;

import com.mlsdev.serhiy.data.net.RestApi;
import com.mlsdev.serhiy.data.net.RestApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Module
public class NetModule {

    @Provides @Singleton
    RestApi provideRestApi(RestApiImpl restApi){
        return restApi;
    }

}
