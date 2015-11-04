package com.mlsdev.serhiy.githubviewer.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.facebook.share.model.ShareLinkContent;
import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowingsUseCase;
import com.mlsdev.serhiy.domain.interactor.abstraction.InteractorCallback;
import com.mlsdev.serhiy.domain.interactor.abstraction.LoadAvatarUseCase;
import com.mlsdev.serhiy.domain.interactor.abstraction.SearchRepositoryUseCase;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;
import com.mlsdev.serhiy.githubviewer.model.mapper.RepositoryDataMapper;
import com.mlsdev.serhiy.githubviewer.view.DetailView;
import com.mlsdev.serhiy.githubviewer.view.adapter.RepositoryListAdapter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.mlsdev.serhiy.githubviewer.presenter.SearchUserPresenter.EXTRA_USER_AVATAR;
import static com.mlsdev.serhiy.githubviewer.presenter.SearchUserPresenter.EXTRA_USER_NAME;
import static com.mlsdev.serhiy.githubviewer.presenter.SearchUserPresenter.EXTRA_USER_PROFILE;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Singleton
public class DetailUserInfoPresenter implements DetailPresenter {

    private Bundle     mUserData;
    private DetailView mView;

    /*   dependencies   */
    private GetFollowersUseCase     mFollowersUseCase;
    private GetFollowingsUseCase    mFollowingsUseCase;
    private SearchRepositoryUseCase mRepositoryUseCase;
    private LoadAvatarUseCase       mLoadAvatarUseCase;
    private RepositoryDataMapper    mRepositoryDataMapper;
    private RepositoryListAdapter   mRepositoryListAdapter;
    Context mContext;

    @Inject
    public DetailUserInfoPresenter(GetFollowersUseCase mFollowersUseCase,
                                   GetFollowingsUseCase mFollowingsUseCase,
                                   SearchRepositoryUseCase mRepositoryUseCase,
                                   LoadAvatarUseCase mLoadAvatarUseCase,
                                   RepositoryDataMapper repositoryDataMapper,
                                   RepositoryListAdapter   repositoryListAdapter,
                                   Context context) {
        this.mFollowersUseCase = mFollowersUseCase;
        this.mFollowingsUseCase = mFollowingsUseCase;
        this.mRepositoryUseCase = mRepositoryUseCase;
        this.mLoadAvatarUseCase = mLoadAvatarUseCase;
        this.mRepositoryDataMapper = repositoryDataMapper;
        this.mRepositoryListAdapter = repositoryListAdapter;
        this.mContext = context;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void setDetailView(@NonNull DetailView detailView) {
        mView = detailView;
    }

    @Override
    public void setupUserData(Bundle userData) {
        mUserData = userData;
    }

    @Override
    public void searchRepositories() {
        String userName = mUserData.getString(EXTRA_USER_NAME, "");
        mRepositoryUseCase.execute(userName, mSerachRepositoriesCallback);
    }

    @Override
    public void loadUserAvatar() {
        String imageUrl = mUserData.getString(EXTRA_USER_AVATAR, "");
        mLoadAvatarUseCase.execute(imageUrl, mLoadAvatarCallback);
    }

    @Override
    public void getFollows() {
        String username = getUsername();
        mFollowersUseCase.execute(username, mGetFollowersCallback);
        mFollowingsUseCase.execute(username, mGetFollowingsCallback);
    }

    @Override
    public void openProfileInBrowser() {
        String profileUrlString = mUserData.getString(EXTRA_USER_PROFILE, "");
        Uri profileUri = Uri.parse(profileUrlString);
        Intent intentToOpenProfileInBrowser = new Intent(Intent.ACTION_VIEW, profileUri);
        intentToOpenProfileInBrowser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (intentToOpenProfileInBrowser.resolveActivity(mContext.getPackageManager()) != null)
            mContext.startActivity(intentToOpenProfileInBrowser);
    }

    @Override
    public void share() {
        Uri profileUri = Uri.parse(mUserData.getString(EXTRA_USER_PROFILE));
        Uri userAvatar = Uri.parse(mUserData.getString(EXTRA_USER_AVATAR));
        String username = getUsername();
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(profileUri)
                .setImageUrl(userAvatar)
                .setContentTitle(username)
                .build();

        mView.share(content);
    }

    private String getUsername(){
        return mUserData.getString(EXTRA_USER_NAME, "");
    }

    private LoadAvatarUseCase.Callback mLoadAvatarCallback = new LoadAvatarUseCase.Callback() {
        @Override
        public void onLoadImageSuccess(Bitmap bitmap) {
            mView.setupUserAvatar(bitmap);
        }

        @Override
        public void onError() {

        }
    };

    private GetFollowersUseCase.Callback mGetFollowersCallback = new GetFollowersUseCase.Callback() {
        @Override
        public void onFollowersLoaded(Integer followersNumber) {
            String followersStr = mContext.getString(R.string.followers, followersNumber);
            mView.setFollowers(followersStr);
        }

        @Override
        public void onError() {
            mView.showError(mContext.getString(R.string.load_followers_error));
        }
    };

    private GetFollowingsUseCase.Callback mGetFollowingsCallback = new GetFollowingsUseCase.Callback() {
        @Override
        public void onFollowingsLoaded(Integer followingsNumber) {
            String followingsString = mContext.getString(R.string.followings, followingsNumber);
            mView.setFollowings(followingsString);
        }

        @Override
        public void onError() {
            mView.showError(mContext.getString(R.string.load_followings_error));
        }
    };

    private InteractorCallback<List<GithubRepository>> mSerachRepositoriesCallback = new InteractorCallback<List<GithubRepository>>() {
        @Override
        public void onSuccess(List<GithubRepository> data) {
            List<RepositoryModel> repositoryModels = mRepositoryDataMapper.transformRepositoryCollectionData(data);
            mRepositoryListAdapter.setData(repositoryModels);
            mView.setListAdapter(mRepositoryListAdapter);
        }

        @Override
        public void onError(String errorMessage) {
            mView.showError(errorMessage);
        }
    };

}
