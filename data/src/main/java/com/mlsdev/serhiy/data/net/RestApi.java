package com.mlsdev.serhiy.data.net;

import android.graphics.Bitmap;

import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Singleton
public interface RestApi {

    /**
     * Callback is used to be notified either a load avatar request was success or an error happened.
     */
    interface LoadAvatarRequestCallback {
        void onRequestSuccess(Bitmap bitmap);

        void onError();
    }

    /**
     * Load user's avatar from the network or cache and gives it to an interactor.
     *
     * @param url                   The url is used to load an avatar from the network or cache;
     * @param avatarRequestCallback A {@link LoadAvatarRequestCallback}
     */
    void loadAvatarRequest(String url, LoadAvatarRequestCallback avatarRequestCallback);

}
