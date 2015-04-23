package com.mlsdev.serhiy.githubviewer.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mlsdev.serhiy.githubviewer.view.DetailView;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */
public interface DetailPresenter extends Presenter {

    /**
     * Method that sets up a view to interact with it.
     * */
    void setDetailView(@NonNull DetailView detailView);

    void setupUserData(@NonNull Bundle userData);
    void searchRepositories();
    void loadUserAvatar();
    void getFollows();
    void openProfileInBrowser();

}
