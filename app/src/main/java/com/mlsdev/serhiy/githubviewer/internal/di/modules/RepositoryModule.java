package com.mlsdev.serhiy.githubviewer.internal.di.modules;

import com.mlsdev.serhiy.data.repository.GithubRepositoryImpl;
import com.mlsdev.serhiy.domain.interactor.abstraction.SearchRepositoryUseCase;
import com.mlsdev.serhiy.domain.interactor.implementation.SearchRepositoryUseCaseImpl;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */

@Module
public class RepositoryModule {

    @Provides
    SearchRepositoryUseCase provideSearchRepositoryUseCase(SearchRepositoryUseCaseImpl repositoryUseCase){
        return repositoryUseCase;
    }

    @Provides @Singleton
    GithubUserRepository providesGithubUserRepository(GithubRepositoryImpl githubRepository){
        return githubRepository;
    }

    @Provides @Singleton
    List<RepositoryModel> provideRepositoryModels(){
        return new ArrayList<RepositoryModel>();
    }

}
