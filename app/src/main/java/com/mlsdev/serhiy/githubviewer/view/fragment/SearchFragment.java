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

/**
 * Created by Serhiy Petrosyuk on 16.04.15.
 */
public class SearchFragment extends Fragment implements SearchView {

    @Inject
    SearchPresenter mPresenter;

    private ImageButton    mSearchButton;
    private EditText       mSearchedNameEditText;
    private ProgressBar    mProgressBar;
    private RelativeLayout mContentHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((App)getActivity().getApplication()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View searchFragment = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search, container, false);

        mSearchButton         = (ImageButton) searchFragment.findViewById(R.id.btn_search);
        mSearchedNameEditText = (EditText) searchFragment.findViewById(R.id.et_user_name);
        mProgressBar          = (ProgressBar) searchFragment.findViewById(R.id.pb_search_screen);
        mContentHolder        = (RelativeLayout) searchFragment.findViewById(R.id.rl_content_holder);
        mSearchButton.setOnClickListener(new OnSearchClickListener());

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

    public class OnSearchClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            mPresenter.searchUser(mSearchedNameEditText.getText().toString());
        }
    }
}
