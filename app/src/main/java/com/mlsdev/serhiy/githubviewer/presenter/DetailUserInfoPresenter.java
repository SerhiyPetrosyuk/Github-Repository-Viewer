package com.mlsdev.serhiy.githubviewer.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.facebook.share.model.ShareLinkContent;
import com.mlsdev.serhiy.domain.interactor.abstraction.BaseSubscriber;
import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowingsUseCase;
import com.mlsdev.serhiy.domain.interactor.abstraction.SearchRepositoryUseCase;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;
import com.mlsdev.serhiy.githubviewer.model.mapper.RepositoryDataMapper;
import com.mlsdev.serhiy.githubviewer.view.DetailView;
import com.mlsdev.serhiy.githubviewer.view.adapter.RepositoryListAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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

    private Bundle mUserData;
    private DetailView mView;

    /*   dependencies   */
    private GetFollowersUseCase mFollowersUseCase;
    private GetFollowingsUseCase mFollowingsUseCase;
    private SearchRepositoryUseCase mRepositoryUseCase;
    private RepositoryDataMapper mRepositoryDataMapper;
    private RepositoryListAdapter mRepositoryListAdapter;
    Context mContext;

    @Inject
    public DetailUserInfoPresenter(GetFollowersUseCase mFollowersUseCase,
                                   GetFollowingsUseCase mFollowingsUseCase,
                                   SearchRepositoryUseCase mRepositoryUseCase,
                                   RepositoryDataMapper repositoryDataMapper,
                                   RepositoryListAdapter repositoryListAdapter,
                                   Context context) {
        this.mFollowersUseCase = mFollowersUseCase;
        this.mFollowingsUseCase = mFollowingsUseCase;
        this.mRepositoryUseCase = mRepositoryUseCase;
        this.mRepositoryDataMapper = repositoryDataMapper;
        this.mRepositoryListAdapter = repositoryListAdapter;
        this.mContext = context;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        mRepositoryUseCase.unsubscribe();
        mFollowersUseCase.unsubscribe();
        mFollowingsUseCase.unsubscribe();
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
        mRepositoryUseCase.execute(userName, new LoadRepositoriesSubscriber());
    }

    @Override
    public void loadUserAvatar() {
        String imageUrl = mUserData.getString(EXTRA_USER_AVATAR, "");
        Target imageTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mView.setupUserAvatar(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.with(mContext).load(imageUrl).into(imageTarget);
    }

    @Override
    public void getFollows() {
        String username = getUsername();
        mFollowersUseCase.execute(username, new LoadFollowersSubscriber());
        mFollowingsUseCase.execute(username, new LoadFollowingsSubscriber());
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

    private String getUsername() {
        return mUserData.getString(EXTRA_USER_NAME, "");
    }

    public class LoadFollowersSubscriber extends BaseSubscriber<Integer> {
        @Override
        public void onNext(Integer followersNumber) {
            String followersStr = mContext.getString(R.string.followers, followersNumber);
            mView.setFollowers(followersStr);
        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }
    }

    public class LoadFollowingsSubscriber extends BaseSubscriber<Integer> {
        @Override
        public void onNext(Integer followingsNumber) {
            String followersStr = mContext.getString(R.string.followings, followingsNumber);
            mView.setFollowings(followersStr);
        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }
    }

    public class LoadRepositoriesSubscriber extends BaseSubscriber<List<GithubRepository>> {
        @Override
        public void onCompleted() {
            mView.stopRepositoryLoading();
        }

        @Override
        public void onNext(List<GithubRepository> githubRepositoryList) {
            List<RepositoryModel> repositoryModels = mRepositoryDataMapper
                    .transformRepositoryCollectionData(githubRepositoryList);
            mRepositoryListAdapter.setData(repositoryModels);
            mView.setListAdapter(mRepositoryListAdapter);
        }

        @Override
        public void onError(Throwable e) {
            mView.showError(e.getMessage());
        }
    }

}
