package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.user.SearchUserResult;
import com.mlsdev.serhiy.domain.model.GithubRepository;

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

    void getRepositories(String userName, final ApiCallback<List<GithubRepository>> callback);

    void getFollowers(String userName, final ApiCallback<Integer> callback);

    void getFollowings(String userName, final ApiCallback<Integer> callback);
}
