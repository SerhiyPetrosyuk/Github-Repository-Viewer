package com.mlsdev.serhiy.githubviewer.internal.di.modules;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowingsUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.GetFollowersUseCaseImpl;
import com.mlsdev.serhiy.domain.interactor.implementation.GetFollowingsUseCaseImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */

@Module
public class FollowModule {

    @Provides
    GetFollowingsUseCase provideGetFollowingsUseCase(GetFollowingsUseCaseImpl followingsUseCase){
        return followingsUseCase;
    }

    @Provides
    GetFollowersUseCase provideGetFollowersUseCase(GetFollowersUseCaseImpl getFollowersUseCase){
        return getFollowersUseCase;
    }

}
