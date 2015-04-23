package com.mlsdev.serhiy.githubviewer.view.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.view.fragment.SearchFragment;


public class SearchActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = new SearchFragment();

        if (savedInstanceState != null){
            fragment = getFragmentManager().findFragmentByTag(SearchFragment.class.getName());
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.fl_search_fragment_holder, fragment, SearchFragment.class.getName())
                .commit();
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }
}
