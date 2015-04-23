package com.mlsdev.serhiy.githubviewer.view;

import android.graphics.Bitmap;
import android.widget.BaseAdapter;

/**
 * Created by Serhiy Petrosyuk on 21.04.15.
 */
public interface DetailView {

    void setupUserAvatar(Bitmap bitmap);
    void setFollowers(String followersString);
    void setFollowings(String followingsString);
    void onLoadAvatarError();
    void setListAdapter(BaseAdapter adapter);
}