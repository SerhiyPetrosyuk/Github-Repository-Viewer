package com.mlsdev.serhiy.githubviewer.presenter;

import android.support.annotation.NonNull;

import com.mlsdev.serhiy.githubviewer.view.SearchView;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

public interface SearchPresenter extends Presenter {

    /**
     * Method that sets up a view to interact with it.
     * */
    void setSearchView(@NonNull SearchView searchView);

    /**
     * Method that searches data via an interactor.
     *
     * @param searchedName The searched name is for searching a user data by username.
     * */
    void searchUser(String searchedName);

}
