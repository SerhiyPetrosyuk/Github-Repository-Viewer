package com.mlsdev.serhiy.githubviewer.internal.di.modules;

import com.mlsdev.serhiy.data.net.GitApiService;
import com.mlsdev.serhiy.data.net.GitApiServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Module
public class NetModule {

    @Provides
    GitApiService provideGitApiService(GitApiServiceImpl gitApiService) {
        return gitApiService;
    }

}
