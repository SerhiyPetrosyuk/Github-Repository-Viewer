package com.mlsdev.serhiy.domain.repository;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */

import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.model.GithubUser;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link GithubUser, GithubUserRepository} related data.
 */
public interface GithubUserRepository {

    interface RepositoryCallBack<T> {
        void onSuccess(T data);

        void onError(String errorMessage);
    }

    /**
     * Search a {@link GithubUser} by name.
     *
     * @param searchedName The searched name is for the searching and retrieving data;
     */
    Observable<GithubUser> searchGithubUserByName(String searchedName);

    /**
     * Search a collection of {@link GithubUserRepository}.
     *
     * @param userName The user's name is for the retrieving data;
     * @param callback A {@link RepositoryCallBack} is for notifying a client.
     */
    void getRepositories(String userName, final RepositoryCallBack<List<GithubRepository>> callback);

    /**
     * Get a followers number by username.
     *
     * @param userName The username is used to get user's followers.
     * @param callback A {@link RepositoryCallBack} is for notifying a client.
     */
    public void getFollowersNumber(String userName, RepositoryCallBack<Integer> callback);

    /**
     * Get a followings number by username.
     *
     * @param userName The username is used to get user's followings.
     * @param callback A {@link RepositoryCallBack} is for notifying a client.
     */
    public void getFollowingsNumber(String userName, RepositoryCallBack<Integer> callback);

}
