package com.mlsdev.serhiy.data.net;

import android.graphics.Bitmap;

import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.model.GithubUser;

import java.util.Collection;

import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Singleton
public interface RestApi {

    public static String BASE_URL = "https://api.github.com/";
    public static String SEARCH_BASE_URL = "search/";
    public static String USERS_BASE_URL = "users/";
    public static String SEARCH_USER_BASE_URL = BASE_URL + SEARCH_BASE_URL + "users?q=";
    public static String SEARCH_REPOSITORY_BASE_URL = BASE_URL + SEARCH_BASE_URL + "repositories?q=user:";
    public static String GET_FOLLOWERS_BASE_URL = BASE_URL + USERS_BASE_URL;
    public static String GET_FOLLOWINGS_BASE_URL = BASE_URL + USERS_BASE_URL;
    public static String FOLLOWERS_PATH = "/followers";
    public static String FOLLOWINGS_PATH = "/following";

    /**
     * Callback is used to be notified either a request was success or an error happened.
     * */
    interface SearchUserRequestCallback{
        void onRequestSuccess(GithubUser githubUser);
        void onError();
    }

    /**
     * Callback is used to be notified either a request was success or an error happened.
     * */
    interface SearchRepositoriesRequestCallback{
        void onRequestSuccess(Collection<GithubRepository> githubRepositories);
        void onError();
    }

    /**
     * Callback is used to be notified either a load avatar request was success or an error happened.
     * */
    interface LoadAvatarRequestCallback{
        void onRequestSuccess(Bitmap bitmap);
        void onError();
    }

    /**
     * Callback is used to be notified either a request was success or an error happened.
     * */
    interface GetFollowersRequestCallback{
        void onGetFollowersSuccess(Integer followersNumber);
        void onError();
    }

    /**
     * Callback is used to be notified either a request was success or an error happened.
     * */
    interface GetFollowingsRequestCallback{
        void onGetFollowingsSuccess(Integer followingsNumber);
        void onError();
    }

    /**
     * Makes request to search a user data.
     *
     * @param callback The {@link SearchUserRequestCallback} is for being notified
     * */
    void searchUserRequest(String searchedName, SearchUserRequestCallback callback);

    /**
     * Makes request to search repositories data.
     *
     * @param callback The {@link SearchRepositoriesRequestCallback} is for being notified
     * */
    void searchRepositoriesRequest(String repositoriesUrl, SearchRepositoriesRequestCallback callback);

    /**
     * Load user's avatar from the network or cache and gives it to an interactor.
     *
     * @param url The url is used to load an avatar from the network or cache;
     * @param avatarRequestCallback A {@link LoadAvatarRequestCallback}*/
    void loadAvatarRequest(String url, LoadAvatarRequestCallback avatarRequestCallback);

    /**
     * Get user's followers number by username
     *
     * @param username The username is needed to get user's followers by its name;
     * @param callback A {@link GetFollowersRequestCallback} is for being notified
     * */
    void getFollowersNumber(String username, GetFollowersRequestCallback callback);

    /**
     * Get user's followers number by username
     *
     * @param username The username is needed to get user's followings by its name;
     * @param callback A {@link GetFollowingsRequestCallback} is for being notified
     * */
    void getFollowingsNumber(String username, GetFollowingsRequestCallback callback);
}
