package com.mlsdev.serhiy.domain.repository;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */

import android.graphics.Bitmap;

import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.model.GithubUser;

import java.util.Collection;
import java.util.List;

/**
 * Interface that represents a Repository for getting {@link GithubUser, GithubUserRepository} related data.
 */
public interface GithubUserRepository {

    interface RepositoryCallBack<T> {
        void onSuccess(T data);

        void onError(String errorMessage);
    }

    /**
     * Callback to be notified when either user has been loaded or an error happened.
     */
    public interface GithubUserCallback {
        void onUserLoaded(GithubUser githubUser);

        void onError();
    }

    /**
     * Callback to be notified when either user's repositories has been loaded or an error happened.
     */
    public interface GithubRepositoryCallback {
        void onRepositoriesLoaded(Collection<GithubRepository> githubRepositories);

        void onError();
    }

    /**
     * Callback to be notified when either user's followers have been loaded or an error happened.
     */
    public interface FollowersCallback {
        void onFollowersLoaded(Integer followersNumber);

        void onError();
    }

    /**
     * Callback to be notified when either user's followings have been loaded or an error happened.
     */
    public interface FollowingsCallback {
        void onFollowingsLoaded(Integer followingsNumber);

        void onError();
    }

    /**
     * Callback to be notified when either user's avatar has been loaded or an error happened.
     */
    public interface LoadAvatarCallback {
        void onAvatarLoaded(Bitmap bitmap);

        void onError();
    }

    /**
     * Search a {@link GithubUser} by name.
     *
     * @param searchedName The searched name is for the searching and retrieving data;
     * @param callback     A {@link GithubUserCallback} is for notifying a client.
     */
    public void searchGithubUserByName(String searchedName, RepositoryCallBack<GithubUser> callback);

    /**
     * Search a collection of {@link GithubUserRepository}.
     *
     * @param userName The user's name is for the retrieving data;
     * @param callback A {@link GithubRepositoryCallback} is for notifying a client.
     */
    void getRepositories(String userName, final RepositoryCallBack<List<GithubRepository>> callback);

    /**
     * Get a followers number by username.
     *
     * @param userName The username is used to get user's followers.
     * @param callback A {@link FollowersCallback} is for notifying a client.
     */
    public void getFollowersNumber(String userName, FollowersCallback callback);

    /**
     * Get a followings number by username.
     *
     * @param userName The username is used to get user's followings.
     * @param callback A {@link FollowingsCallback} is for notifying a client.
     */
    public void getFollowingsNumber(String userName, FollowingsCallback callback);

    /**
     * Load a user's avatar by a url
     *
     * @param url            The url is used to load an avatar from the network or cache;
     * @param avatarCallback A {@link LoadAvatarCallback} is for notifying a client
     */
    public void loadUserAvatar(String url, LoadAvatarCallback avatarCallback);

}
