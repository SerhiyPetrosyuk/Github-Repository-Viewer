package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.InteractorCallback;
import com.mlsdev.serhiy.domain.interactor.abstraction.SearchRepositoryUseCase;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class SearchRepositoryUseCaseImpl implements SearchRepositoryUseCase {
    private GithubUserRepository mRepository;

    @Inject
    public SearchRepositoryUseCaseImpl(GithubUserRepository repository) {
        mRepository = repository;
    }

    @Override
    public void execute(String userName, final InteractorCallback<List<GithubRepository>> callback) {
        mRepository.getRepositories(userName, new GithubUserRepository.RepositoryCallBack<List<GithubRepository>>() {
            @Override
            public void onSuccess(List<GithubRepository> repositoryList) {
                callback.onSuccess(repositoryList);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }
}
