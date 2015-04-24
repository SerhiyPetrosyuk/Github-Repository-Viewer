package com.mlsdev.serhiy.data.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Serhiy Petrosyuk on 21.04.15.
 */
public class RequestExecutor {

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    private RequestExecutor(){

    }

    /**
     * Initialize a request executor to get access to a RequestQueue and ImageLoader
     *
     * @param context An application context.
     * */
    public static void init(Context context){
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(context);
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
        }
    }

    public static RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }



    public static ImageLoader getImageLoader(){
        return mImageLoader;
    }
}
