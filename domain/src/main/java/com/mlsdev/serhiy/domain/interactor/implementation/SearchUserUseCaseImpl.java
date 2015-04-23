package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.SearchUserUseCase;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class SearchUserUseCaseImpl implements SearchUserUseCase {

    private GithubUserRepository mGithubUserRepository;
    private Callback mCallback;

    @Inject
    public SearchUserUseCaseImpl(GithubUserRepository githubUserRepository) {
        this.mGithubUserRepository = githubUserRepository;
    }

    @Override
    public void execute(String searchedName, Callback callback) {
        this.mCallback = callback;
        mGithubUserRepository.searchGithubUserByName(searchedName, mUserCallback);
    }

    private GithubUserRepository.GithubUserCallback mUserCallback = new GithubUserRepository.GithubUserCallback() {
        @Override
        public void onUserLoaded(GithubUser githubUser) {
            mCallback.onUserDataLoaded(githubUser);
        }

        @Override
        public void onError() {
            mCallback.onError();
        }
    };
}
