package com.mlsdev.serhiy.data.net;

import android.graphics.Bitmap;

import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Singleton
public interface RestApi {

    String BASE_URL = "https://api.github.com/";
    String USERS_BASE_URL = "users/";
    String GET_FOLLOWERS_BASE_URL = BASE_URL + USERS_BASE_URL;
    String GET_FOLLOWINGS_BASE_URL = BASE_URL + USERS_BASE_URL;
    String FOLLOWERS_PATH = "/followers";
    String FOLLOWINGS_PATH = "/following";

    /**
     * Callback is used to be notified either a load avatar request was success or an error happened.
     */
    interface LoadAvatarRequestCallback {
        void onRequestSuccess(Bitmap bitmap);

        void onError();
    }

    /**
     * Callback is used to be notified either a request was success or an error happened.
     */
    interface GetFollowersRequestCallback {
        void onGetFollowersSuccess(Integer followersNumber);

        void onError();
    }

    /**
     * Callback is used to be notified either a request was success or an error happened.
     */
    interface GetFollowingsRequestCallback {
        void onGetFollowingsSuccess(Integer followingsNumber);

        void onError();
    }

    /**
     * Load user's avatar from the network or cache and gives it to an interactor.
     *
     * @param url                   The url is used to load an avatar from the network or cache;
     * @param avatarRequestCallback A {@link LoadAvatarRequestCallback}
     */
    void loadAvatarRequest(String url, LoadAvatarRequestCallback avatarRequestCallback);

    /**
     * Get user's followers number by username
     *
     * @param username The username is needed to get user's followers by its name;
     * @param callback A {@link GetFollowersRequestCallback} is for being notified
     */
    void getFollowersNumber(String username, GetFollowersRequestCallback callback);

    /**
     * Get user's followers number by username
     *
     * @param username The username is needed to get user's followings by its name;
     * @param callback A {@link GetFollowingsRequestCallback} is for being notified
     */
    void getFollowingsNumber(String username, GetFollowingsRequestCallback callback);
}
