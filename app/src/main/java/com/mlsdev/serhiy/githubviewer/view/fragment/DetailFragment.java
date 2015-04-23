package com.mlsdev.serhiy.githubviewer.view.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mlsdev.serhiy.githubviewer.App;
import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.presenter.DetailPresenter;
import com.mlsdev.serhiy.githubviewer.presenter.SearchUserPresenter;
import com.mlsdev.serhiy.githubviewer.view.DetailView;
import com.mlsdev.serhiy.githubviewer.view.widget.RoundedView;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 16.04.15.
 */
public class DetailFragment extends Fragment implements DetailView, View.OnClickListener {

    private TextView    mUserNameTextView;
    private TextView    mFollowersTextView;
    private TextView    mFollowingsTextView;
    private ImageView   mUserAvatarImageView;
    private ImageButton mOpenInBrowserButton;
    private Bundle      mUserData;
    private ListView    mListView;
    private ProgressBar mProgressBar;

    @Inject
    DetailPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getActivity().getApplication()).inject(this);
        mUserData = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_detail, container, false);

        mUserNameTextView    = (TextView)   detailFragment.findViewById(R.id.tv_username);
        mUserAvatarImageView = (ImageView)  detailFragment.findViewById(R.id.iv_user_avatar);
        mFollowersTextView   = (TextView)   detailFragment.findViewById(R.id.tv_followers);
        mFollowingsTextView  = (TextView)   detailFragment.findViewById(R.id.tv_following);
        mOpenInBrowserButton = (ImageButton)detailFragment.findViewById(R.id.ib_open_in_web);
        mListView            = (ListView)   detailFragment.findViewById(R.id.lv_repository_list);
        mProgressBar         = (ProgressBar)detailFragment.findViewById(R.id.pd_load_repositories);

        mUserNameTextView.setText(mUserData.getString(SearchUserPresenter.EXTRA_USER_NAME));

        mPresenter.setDetailView(this);
        mPresenter.setupUserData(mUserData);
        mPresenter.loadUserAvatar();
        mPresenter.getFollows();
        mPresenter.searchRepositories();

        mOpenInBrowserButton.setOnClickListener(this);

        return detailFragment;
    }

    @Override
    public void setupUserAvatar(Bitmap bitmap) {
        RoundedView roundedView = new RoundedView(bitmap);
        mUserAvatarImageView.setImageDrawable(roundedView);
    }

    @Override
    public void setFollowers(String followersString) {
        mFollowersTextView.setText(followersString);
    }

    @Override
    public void setFollowings(String followingsString) {
        mFollowingsTextView.setText(followingsString);
    }

    @Override
    public void onLoadAvatarError() {
        Toast.makeText(getActivity(), "Can't load user's avatar!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setListAdapter(BaseAdapter adapter) {
        mListView.setAdapter(adapter);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ib_open_in_web :
                mPresenter.openProfileInBrowser();
                break;
            default:
                break;
        }

    }
}
