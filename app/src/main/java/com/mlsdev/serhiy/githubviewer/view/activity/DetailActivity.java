package com.mlsdev.serhiy.githubviewer.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.view.fragment.DetailFragment;

/**
 * Created by Serhiy Petrosyuk on 16.04.15.
 */
public class DetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent userData = getIntent();
        Fragment fragment = new DetailFragment();

        if (savedInstanceState != null){
            fragment = getSupportFragmentManager().findFragmentByTag(DetailFragment.class.getName());
        } else {
            fragment.setArguments(userData.getExtras());
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fragment_holder, fragment, DetailFragment.class.getName())
                .commit();
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_base;
    }

}
