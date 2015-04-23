package com.mlsdev.serhiy.githubviewer.model;

/**
 * Created by Serhiy Petrosyuk on 21.04.15.
 */
public class UserModel {

    private String mUserName;
    private String mUserProfileLink;
    private String mUserAvatar;
    private String mUserReposLink;
    private Integer mFollowers;
    private Integer mFollowing;

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public void setUserProfileLink(String userProfileLink) {
        this.mUserProfileLink = userProfileLink;
    }

    public void setUserAvatar(String userAvatar) {
        this.mUserAvatar = userAvatar;
    }

    public void setUserReposLink(String mUserReposLink) {
        this.mUserReposLink = mUserReposLink;
    }

    public void setFollowers(Integer followers) {
        this.mFollowers = followers;
    }

    public void setFollowing(Integer following) {
        this.mFollowing = following;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserProfileLink() {
        return mUserProfileLink;
    }

    public String getUserAvatar() {
        return mUserAvatar;
    }

    public String getUserReposLink() {
        return mUserReposLink;
    }

    public Integer getFollowers() {
        return mFollowers;
    }

    public Integer getFollowing() {
        return mFollowing;
    }

}
