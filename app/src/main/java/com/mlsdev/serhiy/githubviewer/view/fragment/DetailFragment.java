package com.mlsdev.serhiy.githubviewer.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mlsdev.serhiy.githubviewer.App;
import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;
import com.mlsdev.serhiy.githubviewer.presenter.DetailPresenter;
import com.mlsdev.serhiy.githubviewer.presenter.SearchUserPresenter;
import com.mlsdev.serhiy.githubviewer.view.DetailView;
import com.mlsdev.serhiy.githubviewer.view.adapter.RecycleAdapter;
import com.mlsdev.serhiy.githubviewer.view.widget.RoundedView;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Serhiy Petrosyuk on 16.04.15.
 */
public class DetailFragment extends Fragment implements DetailView {

    // region the Views' injection
    @InjectView(R.id.tv_username)
    TextView mUserNameTextView;
    @InjectView(R.id.tv_followers)
    TextView mFollowersTextView;
    @InjectView(R.id.tv_following)
    TextView mFollowingsTextView;
    @InjectView(R.id.iv_user_avatar)
    ImageView mUserAvatarImageView;
    @InjectView(R.id.ib_open_in_web)
    ImageButton mOpenInBrowserButton;
    @InjectView(R.id.ib_share)
    ImageButton mShareButton;
    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @InjectView(R.id.pd_load_repositories)
    ProgressBar mProgressBar;
    // endregion
    private RecycleAdapter recycleAdapter;

    private Bundle mUserData;

    @Inject
    DetailPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).inject(this);
        mUserData = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_detail, container, false);

        ButterKnife.inject(this, view);

        mUserNameTextView.setText(mUserData.getString(SearchUserPresenter.EXTRA_USER_NAME));

        mPresenter.setDetailView(this);
        mPresenter.setupUserData(mUserData);
        mPresenter.loadUserAvatar();
        mPresenter.getFollows();
        mPresenter.searchRepositories();
        initRecyclerView();
        return view;
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
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void stopRepositoryLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorText) {
        Toast.makeText(getActivity(), errorText, Toast.LENGTH_LONG).show();
    }

    @Override
    public void share(ShareLinkContent linkContent) {
        ShareDialog.show(this, linkContent);
    }

    @Override
    public void showRepositories(List<RepositoryModel> repositoryModels) {
        recycleAdapter.setData(repositoryModels);
    }

    @OnClick(R.id.ib_open_in_web)
    public void openInBrowserOnClick() {
        mPresenter.openProfileInBrowser();
    }

    @OnClick(R.id.ib_share)
    public void shareOnClick() {
        mPresenter.share();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycleAdapter = new RecycleAdapter(getActivity());
        mRecyclerView.setAdapter(recycleAdapter);
    }
}
