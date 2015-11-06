package com.mlsdev.serhiy.githubviewer.view;

import android.graphics.Bitmap;
import android.widget.BaseAdapter;

import com.facebook.share.model.ShareLinkContent;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;

import java.util.List;

/**
 * Created by Serhiy Petrosyuk on 21.04.15.
 */
public interface DetailView {

    void setupUserAvatar(Bitmap bitmap);
    void setFollowers(String followersString);
    void setFollowings(String followingsString);
    void onLoadAvatarError();
    void setListAdapter(BaseAdapter adapter);
    void stopRepositoryLoading();
    void showError(String errorText);
    void share(ShareLinkContent linkContent);
    void showRepositories(List<RepositoryModel> repositoryModels);
}
