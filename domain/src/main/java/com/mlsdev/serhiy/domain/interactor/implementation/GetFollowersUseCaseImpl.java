package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class GetFollowersUseCaseImpl implements GetFollowersUseCase {

    private GithubUserRepository mRepository;
    private Callback mCallback;

    @Inject
    public GetFollowersUseCaseImpl(GithubUserRepository repository) {
        mRepository = repository;
    }

    @Override
    public void execute(String userName, Callback callback) {
        mCallback = callback;
        mRepository.getFollowersNumber(userName, mFollowersCallback);
    }

    private GithubUserRepository.FollowersCallback mFollowersCallback = new GithubUserRepository.FollowersCallback() {
        @Override
        public void onFollowersLoaded(Integer followersNumber) {
            mCallback.onFollowersLoaded(followersNumber);
        }

        @Override
        public void onError() {
            mCallback.onError();
        }
    };
}
