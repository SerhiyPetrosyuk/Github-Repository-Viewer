package com.mlsdev.serhiy.githubviewer.view;

import android.content.Intent;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */
public interface SearchView {

    void onStartSearch();
    void onStopSearch();
    void onSearchSuccess(Intent userData);
    void onSearchError();

}
