package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.follows.Followers;
import com.mlsdev.serhiy.data.entity.follows.Following;
import com.mlsdev.serhiy.data.entity.mapper.ModelEntityMapper;
import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

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
                .build()
                .create(GitApi.class);
    }

    public void searchUser(String searchedName, final ApiCallback<GithubUser> callback) {
        final Call<SearchUserResult> call = gitApi.searchUser(searchedName);
        call.enqueue(new Callback<SearchUserResult>() {
            @Override
            public void onResponse(Response<SearchUserResult> response, Retrofit retrofit) {
                GithubUser githubUser = mapper.transformUserEntity(response.body().getItems().get(0));
                callback.onSuccess(githubUser);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getRepositories(String userName, final ApiCallback<List<GithubRepository>> callback) {
        final Call<List<RepositoryEntity>> getRepositoryListCall = gitApi.getRepositories(userName);
        getRepositoryListCall.enqueue(new Callback<List<RepositoryEntity>>() {
            @Override
            public void onResponse(Response<List<RepositoryEntity>> response, Retrofit retrofit) {
                callback.onSuccess(mapper.transformRepositoryEntities(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(t.getMessage());
            }
        });
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
