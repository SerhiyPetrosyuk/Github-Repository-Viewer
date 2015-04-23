package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.SearchRepositoryUseCase;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import java.util.Collection;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class SearchRepositoryUseCaseImpl implements SearchRepositoryUseCase {

    private GithubUserRepository mRepository;
    private Callback mCallback;

    @Inject
    public SearchRepositoryUseCaseImpl(GithubUserRepository repository) {
        mRepository = repository;
    }

    @Override
    public void execute(String repositoriesUrl, Callback callback) {
        mCallback = callback;
        mRepository.searchGithubRepositories(repositoriesUrl, mRepositoryCallback);
    }

    private GithubUserRepository.GithubRepositoryCallback mRepositoryCallback = new GithubUserRepository.GithubRepositoryCallback() {
        @Override
        public void onRepositoriesLoaded(Collection<GithubRepository> githubRepositories) {
            mCallback.onUserDataLoaded(githubRepositories);
        }

        @Override
        public void onError() {
            mCallback.onError();
        }
    };
}
