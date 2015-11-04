package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.mapper.ModelEntityMapper;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;
import com.mlsdev.serhiy.domain.model.GithubUser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

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

}
