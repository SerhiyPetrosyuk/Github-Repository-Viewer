package com.mlsdev.serhiy.githubviewer.view;

import com.facebook.share.model.ShareLinkContent;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;

import java.util.List;

/**
 * Created by Serhiy Petrosyuk on 21.04.15.
 */
public interface DetailView {
    void setupUserAvatar(String imageUrl);
    void setFollowers(String followersString);
    void setFollowings(String followingsString);
    void stopRepositoryLoading();
    void showError(String errorText);
    void share(ShareLinkContent linkContent);
    void showRepositories(List<RepositoryModel> repositoryModels);
}
