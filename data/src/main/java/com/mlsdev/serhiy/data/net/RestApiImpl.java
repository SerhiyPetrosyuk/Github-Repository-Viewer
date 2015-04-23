package com.mlsdev.serhiy.data.net;

import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mlsdev.serhiy.data.entity.mapper.EntityJsonMapper;
import com.mlsdev.serhiy.data.net.requests.RequestExecutor;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Singleton
public class RestApiImpl implements RestApi {

    private EntityJsonMapper mJsonMapper;

    /*  Callbacks  */
    private SearchUserRequestCallback         mUserRequestCallback;
    private SearchRepositoriesRequestCallback mRepositoriesRequestCallback;
    private LoadAvatarRequestCallback         mAvatarRequestCallback;
    private GetFollowersRequestCallback       mFollowersRequestCallback;
    private GetFollowingsRequestCallback      mFollowingsRequestCallback;

    @Inject
    public RestApiImpl(EntityJsonMapper jsonMapper) {
        mJsonMapper = jsonMapper;
    }

    @Override
    public void searchUserRequest(String searchedName, SearchUserRequestCallback callback) {
        mUserRequestCallback = callback;

        String url = SEARCH_USER_BASE_URL + searchedName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    String json = jsonObject.toString();
                    mUserRequestCallback.onRequestSuccess(json);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    mUserRequestCallback.onError();
                }
            });

        RequestExecutor.getRequestQueue().add(request);
    }

    @Override
    public void searchRepositoriesRequest(String repositoriesUrl, SearchRepositoriesRequestCallback callback) {
        mRepositoriesRequestCallback = callback;

        JsonArrayRequest request = new JsonArrayRequest(repositoriesUrl,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray jsonArray) {
                            String json = jsonArray.toString();
                            mRepositoriesRequestCallback.onRequestSuccess(json);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            mRepositoriesRequestCallback.onError();
                        }
                    }
                );

        RequestExecutor.getRequestQueue().add(request);
    }

    @Override
    public void loadAvatarRequest(String url, LoadAvatarRequestCallback avatarRequestCallback) {
        mAvatarRequestCallback = avatarRequestCallback;
        ImageRequest imageRequest = new ImageRequest(url, mLoadAvatarListener, 0, 0, null, null);
        RequestExecutor.getRequestQueue().add(imageRequest);
    }

    @Override
    public void getFollowersNumber(String username, GetFollowersRequestCallback callback) {
        mFollowersRequestCallback = callback;
        String url = GET_FOLLOWERS_BASE_URL + username + FOLLOWERS_PATH;

        JsonArrayRequest request = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray jsonArray) {
                            mFollowersRequestCallback.onGetFollowersSuccess(jsonArray.length());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            mFollowersRequestCallback.onError();
                        }
                    }
                );

        RequestExecutor.getRequestQueue().add(request);
    }

    @Override
    public void getFollowingsNumber(String username, GetFollowingsRequestCallback callback) {
        mFollowingsRequestCallback = callback;
        String url = GET_FOLLOWINGS_BASE_URL + username + FOLLOWINGS_PATH;

        JsonArrayRequest request = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray jsonArray) {
                            mFollowingsRequestCallback.onGetFollowingsSuccess(jsonArray.length());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            mFollowingsRequestCallback.onError();
                        }
                    }
                );

        RequestExecutor.getRequestQueue().add(request);
    }

    private Response.Listener<Bitmap> mLoadAvatarListener = new Response.Listener<Bitmap>() {
        @Override
        public void onResponse(Bitmap bitmap) {
            if (bitmap != null) {
                mAvatarRequestCallback.onRequestSuccess(bitmap);
            } else {
                mAvatarRequestCallback.onError();
            }
        }
    };

}
