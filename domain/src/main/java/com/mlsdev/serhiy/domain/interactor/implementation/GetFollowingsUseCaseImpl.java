package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowingsUseCase;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class GetFollowingsUseCaseImpl implements GetFollowingsUseCase {

    private GithubUserRepository mRepository;
    private Callback mCallback;

    @Inject
    public GetFollowingsUseCaseImpl(GithubUserRepository repository) {
        mRepository = repository;
    }

    @Override
    public void execute(String userName, Callback callback) {
        mCallback = callback;
        mRepository.getFollowingsNumber(userName, followingsCallback);
    }

    private GithubUserRepository.FollowingsCallback followingsCallback = new GithubUserRepository.FollowingsCallback() {
        @Override
        public void onFollowingsLoaded(Integer followingsNumber) {
            mCallback.onFollowingsLoaded(followingsNumber);
        }

        @Override
        public void onError() {
            mCallback.onError();
        }
    };
}
