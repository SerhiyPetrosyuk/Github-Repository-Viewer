package com.mlsdev.serhiy.githubviewer.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mlsdev.serhiy.githubviewer.R;

/**
 * Created by Serhiy Petrosyuk on 16.04.15.
 */
public abstract class BaseActivity extends ActionBarActivity {

    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResource());

        mToolbar = (Toolbar) findViewById(R.id.tb_action_bar);

        if (mToolbar != null){
            setSupportActionBar(mToolbar);

            if (getSupportActionBar() != null){
                getSupportActionBar().setLogo(R.mipmap.ic_launcher);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setWindowTitle("");
                getSupportActionBar().setTitle("");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract int getContentResource();

}
