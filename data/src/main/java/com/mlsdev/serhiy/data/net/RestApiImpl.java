package com.mlsdev.serhiy.data.net;

import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

@Singleton
public class RestApiImpl implements RestApi {
    private LoadAvatarRequestCallback         mAvatarRequestCallback;

    @Inject
    public RestApiImpl() {
    }

    @Override
    public void loadAvatarRequest(String url, LoadAvatarRequestCallback avatarRequestCallback) {
        mAvatarRequestCallback = avatarRequestCallback;
        ImageRequest imageRequest = new ImageRequest(url, mLoadAvatarListener, 0, 0, null, null);
        RequestExecutor.getRequestQueue().add(imageRequest);
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
