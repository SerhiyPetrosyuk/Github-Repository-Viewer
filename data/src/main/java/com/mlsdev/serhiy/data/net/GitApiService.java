package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.domain.model.GithubUser;

/**
 * Created by serhiy on 03.11.15.
 */
public interface GitApiService {
    interface ApiCallback<T> {
        void onSuccess(T data);
        void onError(String errorMessage);
    }

    void searchUser(String searchedName, final ApiCallback<GithubUser> callback);

}
