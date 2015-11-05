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
     */
    Observable<List<GithubRepository>> getRepositories(String userName);

    /**
     * Get a followers number by username.
     *
     * @param userName The username is used to get user's followers.
     */
    Observable<Integer> getFollowersNumber(String userName);

    /**
     * Get a followings number by username.
     *
     * @param userName The username is used to get user's followings.
     */
    Observable<Integer> getFollowingsNumber(String userName);

}
