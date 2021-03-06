package com.mlsdev.serhiy.data.repository;

import com.mlsdev.serhiy.data.entity.mapper.ModelEntityMapper;
import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;
import com.mlsdev.serhiy.data.net.GitApiService;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */
@Singleton
public class GithubRepositoryImpl implements GithubUserRepository {
    private GitApiService mGitApiService;
    private ModelEntityMapper mapper;

    @Inject
    public GithubRepositoryImpl(GitApiService gitApiService, ModelEntityMapper mapper) {
        mGitApiService = gitApiService;
        this.mapper = mapper;
    }

    @Override
    public Observable<GithubUser> searchGithubUserByName(String searchedName) {
        return mGitApiService.searchUser(searchedName)
                .map(new Func1<SearchUserResult, GithubUser>() {
                    @Override
                    public GithubUser call(SearchUserResult searchUserResult) {
                        return mapper.transformUserEntity(searchUserResult.getItems().get(0));
                    }
                });
    }

    @Override
    public Observable<List<GithubRepository>> getRepositories(String userName) {
        return mGitApiService.getRepositories(userName)
                .map(new Func1<List<RepositoryEntity>, List<GithubRepository>>() {
                    @Override
                    public List<GithubRepository> call(List<RepositoryEntity> repositoryEntities) {
                        return mapper.transformRepositoryEntities(repositoryEntities);
                    }
                });
    }

    @Override
    public Observable<Integer> getFollowersNumber(String userName) {
        return mGitApiService.getFollowers(userName);
    }

    @Override
    public Observable<Integer> getFollowingsNumber(String userName) {
        return mGitApiService.getFollowings(userName);
    }


}
