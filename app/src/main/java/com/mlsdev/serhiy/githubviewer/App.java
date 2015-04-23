package com.mlsdev.serhiy.githubviewer;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.mlsdev.serhiy.data.net.requests.RequestExecutor;
import com.mlsdev.serhiy.githubviewer.internal.di.components.AppComponent;
import com.mlsdev.serhiy.githubviewer.internal.di.components.DaggerAppComponent;
import com.mlsdev.serhiy.githubviewer.internal.di.modules.AppModule;
import com.mlsdev.serhiy.data.utils.PrefManager;
import com.mlsdev.serhiy.githubviewer.view.fragment.DetailFragment;
import com.mlsdev.serhiy.githubviewer.view.fragment.SearchFragment;

/**
 * Created by Serhiy Petrosyuk on 10.04.15.
 */
public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        this.mAppComponent.inject(this);

        RequestExecutor.init(this); // Initialize a request executor.
        PrefManager.init(this); // Initialize a preference manager.

        FacebookSdk.sdkInitialize(this);
    }

    public void inject(SearchFragment searchFragment){
        mAppComponent.inject(searchFragment);
    }

    public void inject(DetailFragment detailFragment){
        mAppComponent.inject(detailFragment);
    }

}
