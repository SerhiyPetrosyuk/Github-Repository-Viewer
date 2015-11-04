package com.mlsdev.serhiy.data.net;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mlsdev.serhiy.data.entity.mapper.EntityJsonMapper;

import org.json.JSONArray;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Singleton
public class RestApiImpl implements RestApi {

    private EntityJsonMapper mJsonMapper;

    // region Callbacks
    private LoadAvatarRequestCallback         mAvatarRequestCallback;
    private GetFollowersRequestCallback       mFollowersRequestCallback;
    private GetFollowingsRequestCallback      mFollowingsRequestCallback;
    // endregion

    @Inject
    public RestApiImpl(EntityJsonMapper jsonMapper) {
        mJsonMapper = jsonMapper;
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
