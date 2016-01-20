package com.mlsdev.serhiy.githubviewer.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
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

import static com.mlsdev.serhiy.githubviewer.presenter.SearchUserPresenter.EXTRA_USER_NAME;

public class DetailFragment extends Fragment implements DetailView {
    private DetailViewModel viewModel;
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
        viewModel = new DetailViewModel();
        binding = DataBindingUtil.bind(view);
        binding.setViewModel(viewModel);
        viewModel.userName.set(mUserData.getString(EXTRA_USER_NAME));
        mPresenter.setDetailView(this);
        mPresenter.setupUserData(mUserData);
        mPresenter.loadUserAvatar();
        mPresenter.getFollows();
        mPresenter.searchRepositories();
        initRecyclerView();
        return view;
    }

    @Override
    public void setupUserAvatar(String imageUrl) {
        viewModel.imageUrl.set(imageUrl);
    }

    @Override
    public void setFollowers(String followersString) {
        viewModel.followers.set(followersString);
    }

    @Override
    public void setFollowings(String followingsString) {
        viewModel.followings.set(followingsString);
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

    public class DetailViewModel {
        public final ObservableField<String> userName = new ObservableField<>();
        public final ObservableField<String> imageUrl = new ObservableField<>();
        public final ObservableField<String> followers = new ObservableField<>();
        public final ObservableField<String> followings = new ObservableField<>();

        public DetailViewModel() {
            userName.set(null);
            followers.set(null);
            followings.set(null);
            imageUrl.set(null);
        }

        public void onOpenWebVersion(View view) {
            mPresenter.openProfileInBrowser();
        }

        public void onShare(View view) {
            mPresenter.share();
        }
    }
}
