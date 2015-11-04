package com.mlsdev.serhiy.data.repository;

import android.graphics.Bitmap;

import com.mlsdev.serhiy.data.net.GitApiService;
import com.mlsdev.serhiy.data.net.RestApi;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import java.util.List;

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
    private LoadAvatarCallback loadAvatarCallback;

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
    public void getRepositories(String userName, final RepositoryCallBack<List<GithubRepository>> callback) {
        mGitApiService.getRepositories(
                userName,
                new GitApiService.ApiCallback<List<GithubRepository>>() {
                    @Override
                    public void onSuccess(List<GithubRepository> githubRepositoryList) {
                        callback.onSuccess(githubRepositoryList);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        callback.onError(errorMessage);
                    }
                }
        );
    }

    @Override
    public void getFollowersNumber(String userName, final RepositoryCallBack<Integer> callback) {
        mGitApiService.getFollowers(userName, new GitApiService.ApiCallback<Integer>() {
            @Override
            public void onSuccess(Integer followers) {
                callback.onSuccess(followers);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void getFollowingsNumber(String userName, final RepositoryCallBack<Integer> callback) {
        mGitApiService.getFollowings(userName, new GitApiService.ApiCallback<Integer>() {
            @Override
            public void onSuccess(Integer followings) {
                callback.onSuccess(followings);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onError(errorMessage);
            }
        });
    }

    @Override
    public void loadUserAvatar(String url, LoadAvatarCallback avatarCallback) {
        loadAvatarCallback = avatarCallback;
        mRestApi.loadAvatarRequest(url, loadAvatarRequestCallback);
    }

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


}
