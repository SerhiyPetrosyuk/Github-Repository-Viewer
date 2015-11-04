package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by serhiy on 02.11.15.
 */
public interface GitApi {
    String BASE_URL = "https://api.github.com/";
    String USERS = "users";
    String REPOS = "repos";
    String SEARCH = "search";
    String SEARCH_QUERY = "q";
    String USER_NAME_PATH = "userName";

    @GET(SEARCH + "/" + USERS)
    Call<SearchUserResult> searchUser(@Query(SEARCH_QUERY) String searchQuery);

    @GET(USERS + "/{" + USER_NAME_PATH + "}/" + REPOS)
    Call<List<RepositoryEntity>> getRepositories(@Path(USER_NAME_PATH) String userName);

}
