package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.follows.Followers;
import com.mlsdev.serhiy.data.entity.follows.Following;
import com.mlsdev.serhiy.data.entity.mapper.ModelEntityMapper;
import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by serhiy on 03.11.15.
 */
public class GitApiServiceImpl implements GitApiService {
    private GitApi gitApi;
    private ModelEntityMapper mapper;
    private OkHttpClient okHttpClient;

    @Inject
    public GitApiServiceImpl(ModelEntityMapper mapper) {
        this.mapper = mapper;
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
    public void getFollowers(String userName, final ApiCallback<Integer> callback) {
        final Call<List<Followers>> getFollowersCall = gitApi.getFollowers(userName);
        getFollowersCall.enqueue(new Callback<List<Followers>>() {
            @Override
            public void onResponse(Response<List<Followers>> response, Retrofit retrofit) {
                callback.onSuccess(response.body().size());
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getFollowings(String userName, final ApiCallback<Integer> callback) {
        final Call<List<Following>> getFollowingsCall = gitApi.getFollowings(userName);
        getFollowingsCall.enqueue(new Callback<List<Following>>() {
            @Override
            public void onResponse(Response<List<Following>> response, Retrofit retrofit) {
                callback.onSuccess(response.body().size());
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

}
