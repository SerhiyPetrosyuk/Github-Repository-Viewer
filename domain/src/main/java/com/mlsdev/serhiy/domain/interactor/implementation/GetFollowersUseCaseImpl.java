package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.interactor.abstraction.InteractorCallback;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class GetFollowersUseCaseImpl implements GetFollowersUseCase {
    private GithubUserRepository mRepository;

    @Inject
    public GetFollowersUseCaseImpl(GithubUserRepository repository) {
        mRepository = repository;
    }

    @Override
    public void execute(String userName, final InteractorCallback<Integer> callback) {
        mRepository.getFollowersNumber(userName, new GithubUserRepository.RepositoryCallBack<Integer>() {
            @Override
            public void onSuccess(Integer followers) {
                callback.onSuccess(followers);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

}
