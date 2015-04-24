package com.mlsdev.serhiy.data.net.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;
import com.mlsdev.serhiy.data.entity.mapper.EntityJsonMapper;
import com.mlsdev.serhiy.data.entity.mapper.ModelEntityMapper;
import com.mlsdev.serhiy.data.entity.user.Item;

/**
 * Created by Serhiy Petrosyuk on 24.04.15.
 */
public class SearchUserRequest<Model, Entity> extends Request<Model> {
    private Response.Listener<Model> mListener;
    private EntityJsonMapper mJsonMapper;
    private ModelEntityMapper mModelEntityMapper;

    public SearchUserRequest(int method, String url, Response.ErrorListener listener,
                             Response.Listener<Model> responseListener) {
        super(method, url, listener);
        mListener = responseListener;
        mJsonMapper = new EntityJsonMapper();
        mModelEntityMapper = new ModelEntityMapper();
    }

    @Override
    protected Response<Model> parseNetworkResponse(NetworkResponse networkResponse) {

        try {
            String json = new String(networkResponse.data);
            Entity userEntity = (Entity) mJsonMapper.transformUser(json);
            Model githubUser = (Model) mModelEntityMapper.transformUserEntity((Item) userEntity);
            return Response.success(githubUser, HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(Model response) {
        mListener.onResponse(response);
    }
}
