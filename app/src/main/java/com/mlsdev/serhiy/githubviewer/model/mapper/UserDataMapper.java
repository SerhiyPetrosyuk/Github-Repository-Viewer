package com.mlsdev.serhiy.githubviewer.model.mapper;

/**
 * Created by Serhiy Petrosyuk on 21.04.15.
 */

import com.mlsdev.serhiy.domain.model.GithubUser;
import com.mlsdev.serhiy.githubviewer.model.UserModel;

import javax.inject.Inject;

/**
 * Class is for transforming a user data from domain layer into a presentation.model
 * */
public class UserDataMapper {

    @Inject
    public UserDataMapper() {
    }

    UserModel transformUserData(GithubUser githubUser){
        UserModel userModel = new UserModel();

        if (githubUser != null){
            userModel.setUserName(githubUser.getUserName());
            userModel.setUserProfileLink(githubUser.getUserProfileLink());
            userModel.setUserAvatar(githubUser.getUserAvatar());
            userModel.setUserReposLink(githubUser.getUserReposLink());
        }

        return userModel;
    }
}
