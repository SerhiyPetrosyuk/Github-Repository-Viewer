package com.mlsdev.serhiy.githubviewer.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serhiy on 06.11.15.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RepositoryHolder> {
    private List<RepositoryModel> repositoryModels;
    private Context context;

    public RecycleAdapter(Context context) {
        this.context = context;
        this.repositoryModels = new ArrayList<>();
    }

    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_list_item, parent, false);
        return new RepositoryHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {
        holder.forksNumber.setText(repositoryModels.get(position).getForksNumber().toString());
        holder.language.setText(context.getString(R.string.language, repositoryModels.get(position).getLanguage()));
        holder.repositoryName.setText(repositoryModels.get(position).getName());
        holder.starsNumber.setText(repositoryModels.get(position).getStarsNumber().toString());
    }

    @Override
    public int getItemCount() {
        return repositoryModels.size();
    }

    public class RepositoryHolder extends RecyclerView.ViewHolder {
        TextView repositoryName;
        TextView language;
        TextView forksNumber;
        TextView starsNumber;

        public RepositoryHolder(View itemView) {
            super(itemView);
            repositoryName = (TextView) itemView.findViewById(R.id.tv_repository_name);
            language = (TextView) itemView.findViewById(R.id.tv_language_label);
            forksNumber = (TextView) itemView.findViewById(R.id.tv_forks_number);
            starsNumber = (TextView) itemView.findViewById(R.id.tv_stars_number);
        }
    }

    public void setData(List<RepositoryModel> repositoryModels) {
        this.repositoryModels = repositoryModels;
        notifyDataSetChanged();
    }
}
