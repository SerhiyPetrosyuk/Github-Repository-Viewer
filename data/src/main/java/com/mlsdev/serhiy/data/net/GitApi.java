package com.mlsdev.serhiy.data.net;

import com.mlsdev.serhiy.data.entity.user.SearchUserResult;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by serhiy on 02.11.15.
 */
public interface GitApi {
    String BASE_URL = "https://api.github.com/";
    String USERS = "users";
    String SEARCH = "search";
    String SEARCH_QUERY = "q";

    @GET(SEARCH + "/" + USERS)
    Call<SearchUserResult> searchUser(@Query(SEARCH_QUERY) String searchQuery);

}
