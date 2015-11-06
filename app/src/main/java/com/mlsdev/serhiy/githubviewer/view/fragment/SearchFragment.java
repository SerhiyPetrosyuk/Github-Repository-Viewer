package com.mlsdev.serhiy.githubviewer.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mlsdev.serhiy.githubviewer.App;
import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.presenter.SearchPresenter;
import com.mlsdev.serhiy.githubviewer.view.SearchView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Serhiy Petrosyuk on 16.04.15.
 */
public class SearchFragment extends Fragment implements SearchView {
    @Inject
    SearchPresenter mPresenter;

    @InjectView(R.id.btn_search)
    ImageButton mSearchButton;
    @InjectView(R.id.et_user_name)
    EditText mSearchedNameEditText;
    @InjectView(R.id.pb_search_screen)
    ProgressBar mProgressBar;
    @InjectView(R.id.rl_searsh_btn_holder)
    RelativeLayout mContentHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((App) getActivity().getApplication()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search, container, false);

        ButterKnife.inject(this, searchFragment);
        mPresenter.setSearchView(this);

        return searchFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    public void onStartSearch() {
        mContentHolder.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopSearch() {
        mContentHolder.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSearchSuccess(Intent userData) {
        startActivity(userData);
    }

    @Override
    public void onSearchError() {
        Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btn_search)
    public void searchViewOnClick() {
        mPresenter.searchUser(mSearchedNameEditText.getText().toString());
    }

}
