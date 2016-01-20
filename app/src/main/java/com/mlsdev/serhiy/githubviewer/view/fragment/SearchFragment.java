package com.mlsdev.serhiy.githubviewer.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mlsdev.serhiy.githubviewer.App;
import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.databinding.FragmentSearchBinding;
import com.mlsdev.serhiy.githubviewer.presenter.SearchPresenter;
import com.mlsdev.serhiy.githubviewer.view.SearchView;

import javax.inject.Inject;

/**
 * Created by Serhiy Petrosyuk on 16.04.15.
 */
public class SearchFragment extends Fragment implements SearchView, View.OnClickListener {
    @Inject
    SearchPresenter mPresenter;
    private FragmentSearchBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((App) getActivity().getApplication()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search, container, false);
        binding = DataBindingUtil.bind(view);
        binding.setOnClickListener(this);
        mPresenter.setSearchView(this);
        return view;
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
        binding.rlSearshBtnHolder.setVisibility(View.GONE);
        binding.pbSearchScreen.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopSearch() {
        binding.rlSearshBtnHolder.setVisibility(View.VISIBLE);
        binding.pbSearchScreen.setVisibility(View.GONE);
    }

    @Override
    public void onSearchSuccess(Intent userData) {
        startActivity(userData);
    }

    @Override
    public void onSearchError() {
        binding.rlSearshBtnHolder.setVisibility(View.VISIBLE);
        binding.pbSearchScreen.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        mPresenter.searchUser(binding.etUserName.getText().toString());
    }
}
