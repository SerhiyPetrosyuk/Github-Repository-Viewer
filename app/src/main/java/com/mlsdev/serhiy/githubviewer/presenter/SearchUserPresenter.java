package com.mlsdev.serhiy.githubviewer.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.mlsdev.serhiy.domain.interactor.abstraction.SearchUserUseCase;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.mlsdev.serhiy.githubviewer.view.SearchView;
import com.mlsdev.serhiy.githubviewer.view.activity.DetailActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */
@Singleton
public class SearchUserPresenter implements SearchPresenter {

    public static final String EXTRA_USER_NAME = "user_name_extra";
    public static final String EXTRA_USER_PROFILE = "user_profile_link";
    public static final String EXTRA_USER_AVATAR = "user_avatar_link";
    public static final String EXTRA_USER_REPOS = "user_repositories_link";

    private SearchView mSearchView;

    private SearchUserUseCase mSearchUserUseCase;
    @Inject
    Context context;

    @Inject
    public SearchUserPresenter(SearchUserUseCase searchUserUseCase) {
        this.mSearchUserUseCase = searchUserUseCase;
    }

    @Override
    public void setSearchView(@NonNull SearchView searchView) {
        this.mSearchView = searchView;
    }

    @Override
    public void searchUser(String searchedName) {
        mSearchView.onStartSearch();
        mSearchUserUseCase.execute(searchedName, callback);
    }

    @Override
    public void resume() {
        mSearchView.onStopSearch();
    }

    @Override
    public void pause() {

    }

    private SearchUserUseCase.Callback callback = new SearchUserUseCase.Callback() {
        @Override
        public void onUserDataLoaded(GithubUser githubUser) {
            Intent userData = new Intent(context, DetailActivity.class);
            userData.putExtra(EXTRA_USER_NAME, githubUser.getUserName());
            userData.putExtra(EXTRA_USER_PROFILE, githubUser.getUserProfileLink());
            userData.putExtra(EXTRA_USER_AVATAR, githubUser.getUserAvatar());
            userData.putExtra(EXTRA_USER_REPOS, githubUser.getUserReposLink());
            mSearchView.onSearchSuccess(userData);
        }

        @Override
        public void onError() {
            mSearchView.onStopSearch();
            mSearchView.onSearchError();
        }
    };
}
