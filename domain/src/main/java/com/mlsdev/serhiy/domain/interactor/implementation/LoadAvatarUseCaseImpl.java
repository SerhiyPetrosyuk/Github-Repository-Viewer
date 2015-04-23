package com.mlsdev.serhiy.domain.interactor.implementation;

import android.graphics.Bitmap;

import com.mlsdev.serhiy.domain.interactor.abstraction.LoadAvatarUseCase;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 22.04.15.
 */
public class LoadAvatarUseCaseImpl implements LoadAvatarUseCase {

    private GithubUserRepository mRepository;
    private Callback mCallback;

    @Inject
    public LoadAvatarUseCaseImpl(GithubUserRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    public void execute(String avatarUrl, Callback callback) {
        mCallback = callback;
        mRepository.loadUserAvatar(avatarUrl, mLoadAvatarCallback);
    }

    private GithubUserRepository.LoadAvatarCallback mLoadAvatarCallback = new GithubUserRepository.LoadAvatarCallback() {
        @Override
        public void onAvatarLoaded(Bitmap bitmap) {
            mCallback.onLoadImageSuccess(bitmap);
        }

        @Override
        public void onError() {
            mCallback.onError();
        }
    };
}
