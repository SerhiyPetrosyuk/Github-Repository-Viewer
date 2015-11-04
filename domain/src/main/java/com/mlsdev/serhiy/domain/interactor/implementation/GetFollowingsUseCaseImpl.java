package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowingsUseCase;
import com.mlsdev.serhiy.domain.interactor.abstraction.InteractorCallback;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class GetFollowingsUseCaseImpl implements GetFollowingsUseCase {
    private GithubUserRepository mRepository;

    @Inject
    public GetFollowingsUseCaseImpl(GithubUserRepository repository) {
        mRepository = repository;
    }

    @Override
    public void execute(String userName, final InteractorCallback<Integer> callback) {
        mRepository.getFollowingsNumber(
                userName,
                new GithubUserRepository.RepositoryCallBack<Integer>() {
                    @Override
                    public void onSuccess(Integer followings) {
                        callback.onSuccess(followings);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        callback.onError(errorMessage);
                    }
                }
        );
    }

}
