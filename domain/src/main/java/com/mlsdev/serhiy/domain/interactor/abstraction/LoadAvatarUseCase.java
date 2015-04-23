package com.mlsdev.serhiy.domain.interactor.abstraction;

import android.graphics.Bitmap;

/**
 * Created by Serhiy Petrosyuk on 22.04.15.
 */
public interface LoadAvatarUseCase {

    /**
     * Callback is used to be notified either image data has benn loaded or an error happened
     * */
    interface Callback{
        void onLoadImageSuccess(Bitmap bitmap);
        void onError();
    }

    /**
     * Execute a use case.
     *
     * @param avatarUrl The user's avatar url is for loading an avatar from the network or cache;
     * @param callback A {@link Callback} is for notifying a client.
     * */
    void execute(String avatarUrl, Callback callback);

}
