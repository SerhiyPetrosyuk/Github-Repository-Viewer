package com.mlsdev.serhiy.githubviewer.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 23.04.15.
 */

@Singleton
public class RepositoryListAdapter extends BaseAdapter {

    private List<RepositoryModel> mRepositoryModels;
    private Context mContext;

    @Inject
    public RepositoryListAdapter(List<RepositoryModel> mRepositoryModels, Context mContext) {
        this.mRepositoryModels = mRepositoryModels;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mRepositoryModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mRepositoryModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.repository_list_item, parent, false);
            TextView repositoryName = (TextView) convertView.findViewById(R.id.tv_repository_name);
            TextView language = (TextView) convertView.findViewById(R.id.tv_language_label);
            TextView forksNumber = (TextView) convertView.findViewById(R.id.tv_forks_number);
            TextView starsNumber = (TextView) convertView.findViewById(R.id.tv_stars_number);

            Holder holder = new Holder(repositoryName, language, forksNumber, starsNumber);
            convertView.setTag(holder);
        }

        Holder holder = (Holder) convertView.getTag();

        holder.repositoryName.setText(mRepositoryModels.get(position).getName());
        holder.language.setText(mContext.getString(R.string.language, mRepositoryModels.get(position).getLanguage()));
        holder.forksNumber.setText(mRepositoryModels.get(position).getForksNumber().toString());
        holder.starsNumber.setText(mRepositoryModels.get(position).getStarsNumber().toString());

        return convertView;
    }

    /**
     * Class is needed to hold repository data
     * */
    private class Holder{
        TextView repositoryName;
        TextView language;
        TextView forksNumber;
        TextView starsNumber;

        private Holder(TextView repositoryName, TextView language, TextView forksNumber, TextView starsNumber) {
            this.repositoryName = repositoryName;
            this.language = language;
            this.forksNumber = forksNumber;
            this.starsNumber = starsNumber;
        }
    }

    public void setData(List<RepositoryModel> data){
        mRepositoryModels = data;
    }
}
