package com.mlsdev.serhiy.githubviewer.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.mlsdev.serhiy.githubviewer.databinding.FragmentDetailBinding;
import com.mlsdev.serhiy.githubviewer.view.DetailView;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */
public interface DetailPresenter extends Presenter {
    void setDetailView(@NonNull DetailView detailView);
    void setupUserData(@NonNull Bundle userData, @NonNull FragmentDetailBinding detailBinding);
    void searchRepositories();
    void getFollows();
    void openProfileInBrowser();
    void share();
}
