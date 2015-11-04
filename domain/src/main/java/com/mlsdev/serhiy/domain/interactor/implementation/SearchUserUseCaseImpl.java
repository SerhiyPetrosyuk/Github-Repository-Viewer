package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.InteractorCallback;
import com.mlsdev.serhiy.domain.interactor.abstraction.SearchUserUseCase;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class SearchUserUseCaseImpl implements SearchUserUseCase {

    private GithubUserRepository mGithubUserRepository;

    @Inject
    public SearchUserUseCaseImpl(GithubUserRepository githubUserRepository) {
        this.mGithubUserRepository = githubUserRepository;
    }

    @Override
    public void execute(String searchedName, final InteractorCallback<GithubUser> callback) {
        mGithubUserRepository.searchGithubUserByName(searchedName, new GithubUserRepository.RepositoryCallBack<GithubUser>() {
            @Override
            public void onSuccess(GithubUser data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

}
