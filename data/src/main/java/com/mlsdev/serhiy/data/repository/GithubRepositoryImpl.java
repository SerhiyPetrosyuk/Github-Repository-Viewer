package com.mlsdev.serhiy.data.repository;

import android.graphics.Bitmap;

import com.mlsdev.serhiy.data.net.GitApiService;
import com.mlsdev.serhiy.data.net.RestApi;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */
@Singleton
public class GithubRepositoryImpl implements GithubUserRepository {

    private RestApi mRestApi;
    private GitApiService mGitApiService;

    /*   Callbacks   */
    private GithubRepositoryCallback githubRepositoryCallback;
    private LoadAvatarCallback       loadAvatarCallback;
    private FollowersCallback        followersCallback;
    private FollowingsCallback       followingsCallback;

    @Inject
    public GithubRepositoryImpl(RestApi restApi, GitApiService gitApiService) {
        mRestApi = restApi;
        mGitApiService = gitApiService;
    }

    @Override
    public void searchGithubUserByName(String searchedName, final RepositoryCallBack<GithubUser> callback) {
        mGitApiService.searchUser(searchedName, new GitApiService.ApiCallback<GithubUser>() {
            @Override
            public void onSuccess(GithubUser data) {
                callback.onSuccess(data);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void searchGithubRepositories(String repositoriesUrl, GithubRepositoryCallback callback) {
        githubRepositoryCallback = callback;
        mRestApi.searchRepositoriesRequest(repositoriesUrl, repositoriesRequestCallback);
    }

    @Override
    public void getFollowersNumber(String userName, FollowersCallback callback) {
        followersCallback = callback;
        mRestApi.getFollowersNumber(userName, followersRequestCallback);
    }

    @Override
    public void getFollowingsNumber(String userName, FollowingsCallback callback) {
        followingsCallback = callback;
        mRestApi.getFollowingsNumber(userName, followingsRequestCallback);
    }

    @Override
    public void loadUserAvatar(String url, LoadAvatarCallback avatarCallback) {
        loadAvatarCallback = avatarCallback;
        mRestApi.loadAvatarRequest(url, loadAvatarRequestCallback);
    }

    private RestApi.SearchRepositoriesRequestCallback repositoriesRequestCallback = new RestApi.SearchRepositoriesRequestCallback() {
        @Override
        public void onRequestSuccess(Collection<GithubRepository> githubRepositories) {
            githubRepositoryCallback.onRepositoriesLoaded(githubRepositories);
        }

        @Override
        public void onError() {
            githubRepositoryCallback.onError();
        }
    };

    private RestApi.LoadAvatarRequestCallback loadAvatarRequestCallback = new RestApi.LoadAvatarRequestCallback() {
        @Override
        public void onRequestSuccess(Bitmap bitmap) {
            loadAvatarCallback.onAvatarLoaded(bitmap);
        }

        @Override
        public void onError() {
            loadAvatarCallback.onError();
        }
    };

    private RestApi.GetFollowersRequestCallback followersRequestCallback = new RestApi.GetFollowersRequestCallback() {
        @Override
        public void onGetFollowersSuccess(Integer followersNumber) {
            followersCallback.onFollowersLoaded(followersNumber);
        }

        @Override
        public void onError() {
            followersCallback.onError();
        }
    };

    private RestApi.GetFollowingsRequestCallback followingsRequestCallback = new RestApi.GetFollowingsRequestCallback() {
        @Override
        public void onGetFollowingsSuccess(Integer followingsNumber) {
            followingsCallback.onFollowingsLoaded(followingsNumber);
        }

        @Override
        public void onError() {
            followingsCallback.onError();
        }
    };

    // NEW //


}
