package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;

import java.util.List;

import rx.Observable;

/**
 * Created by serhiy on 03.11.15.
 */
public interface GitApiService {
    interface ApiCallback<T> {
        void onSuccess(T data);

        void onError(String errorMessage);
    }

    Observable<SearchUserResult> searchUser(String searchedName);

    Observable<List<RepositoryEntity>> getRepositories(String userName);

    void getFollowers(String userName, final ApiCallback<Integer> callback);

    void getFollowings(String userName, final ApiCallback<Integer> callback);
}
