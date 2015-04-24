package com.mlsdev.serhiy.data.net.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.mlsdev.serhiy.data.entity.mapper.EntityJsonMapper;
import com.mlsdev.serhiy.data.entity.mapper.ModelEntityMapper;

import java.util.Collection;

/**
 * Created by Serhiy Petrosyuk on 24.04.15.
 */
public class SearchRepositoryRequest<Model, Entity> extends Request<Collection<Model>> {
    private Response.Listener<Collection<Model>> mListener;
    private EntityJsonMapper mJsonMapper;
    private ModelEntityMapper mModelEntityMapper;

    public SearchRepositoryRequest(int method, String url, Response.ErrorListener listener,
                                   Response.Listener<Collection<Model>> responseListener) {
        super(method, url, listener);
        mListener = responseListener;
        mJsonMapper = new EntityJsonMapper();
        mModelEntityMapper = new ModelEntityMapper();
    }

    @Override
    protected Response<Collection<Model>> parseNetworkResponse(NetworkResponse networkResponse) {
        String json = new String(networkResponse.data);
        Collection<Entity> entities = (Collection<Entity>) mJsonMapper.transformRepositoryCollection(json);
        Collection<Model> models = (Collection<Model>) mModelEntityMapper.transformRepositoryEntities(
                    (Collection<com.mlsdev.serhiy.data.entity.repository.Item>) entities
                );
        return Response.success(models, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @Override
    protected void deliverResponse(Collection<Model> models) {
        mListener.onResponse(models);
    }

}
