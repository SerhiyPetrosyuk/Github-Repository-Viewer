package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;

import java.util.List;

import rx.Observable;

/**
 * Created by serhiy on 03.11.15.
 */
public interface GitApiService {
    Observable<SearchUserResult> searchUser(String searchedName);

    Observable<List<RepositoryEntity>> getRepositories(String userName);

    Observable<Integer> getFollowers(String userName);

    Observable<Integer> getFollowings(String userName);
}
