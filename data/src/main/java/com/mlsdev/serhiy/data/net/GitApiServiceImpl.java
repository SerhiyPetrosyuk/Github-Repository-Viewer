package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.follows.Followers;
import com.mlsdev.serhiy.data.entity.follows.Following;
import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;

import javax.inject.Inject;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by serhiy on 03.11.15.
 */
public class GitApiServiceImpl implements GitApiService {
    private GitApi gitApi;
    private OkHttpClient okHttpClient;

    @Inject
    public GitApiServiceImpl() {
        okHttpClient = new OkHttpClient();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.interceptors().add(logging);

        gitApi = new Retrofit.Builder()
                .baseUrl(GitApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(GitApi.class);
    }

    @Override
    public Observable<SearchUserResult> searchUser(String searchedName) {
        return gitApi.searchUserObservable(searchedName);
    }

    @Override
    public Observable<List<RepositoryEntity>> getRepositories(String userName) {
        return gitApi.getRepositories(userName);
    }

    @Override
    public Observable<Integer> getFollowers(String userName) {
        return gitApi.getFollowers(userName)
                .map(new Func1<List<Followers>, Integer>() {
                    @Override
                    public Integer call(List<Followers> followersList) {
                        return followersList.size();
                    }
                });
    }

    @Override
    public Observable<Integer> getFollowings(String userName) {
        return gitApi.getFollowings(userName)
                .map(new Func1<List<Following>, Integer>() {
                    @Override
                    public Integer call(List<Following> followingList) {
                        return followingList.size();
                    }
                });
    }


}
