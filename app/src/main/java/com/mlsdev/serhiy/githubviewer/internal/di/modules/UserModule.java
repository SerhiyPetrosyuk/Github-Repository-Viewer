package com.mlsdev.serhiy.githubviewer.internal.di.modules;

import com.mlsdev.serhiy.domain.interactor.abstraction.SearchUserUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.SearchUserUseCaseImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */

@Module
public class UserModule {

    @Provides
    SearchUserUseCase provideSearchUserUseCase(SearchUserUseCaseImpl searchUserUseCase){
        return searchUserUseCase;
    }

}
