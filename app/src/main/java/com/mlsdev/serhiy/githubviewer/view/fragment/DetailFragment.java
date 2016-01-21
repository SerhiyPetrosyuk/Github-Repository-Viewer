package com.mlsdev.serhiy.githubviewer.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.mlsdev.serhiy.githubviewer.App;
import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.databinding.FragmentDetailBinding;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;
import com.mlsdev.serhiy.githubviewer.presenter.DetailPresenter;
import com.mlsdev.serhiy.githubviewer.view.DetailView;
import com.mlsdev.serhiy.githubviewer.view.adapter.RecycleAdapter;

import java.util.List;

import javax.inject.Inject;

public class DetailFragment extends Fragment implements DetailView {
    private RecycleAdapter recycleAdapter;
    private FragmentDetailBinding binding;
    private Bundle mUserData;

    @Inject
    DetailPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).inject(this);
        mUserData = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_detail, container, false);
        binding = DataBindingUtil.bind(view);
        mPresenter.setDetailView(this);
        mPresenter.setupUserData(mUserData, binding);
        mPresenter.getFollows();
        mPresenter.searchRepositories();
        initRecyclerView();
        return view;
    }

    @Override
    public void stopRepositoryLoading() {
        binding.pdLoadRepositories.setVisibility(View.GONE);
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

    private void initRecyclerView() {
        recycleAdapter = new RecycleAdapter(getActivity());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(recycleAdapter);
    }

}
