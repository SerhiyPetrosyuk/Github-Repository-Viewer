package com.mlsdev.serhiy.githubviewer.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mlsdev.serhiy.githubviewer.R;
import com.mlsdev.serhiy.githubviewer.databinding.RepositoryListItemBinding;
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
        RepositoryItemViewModel viewModel = new RepositoryItemViewModel(
                repositoryModels.get(position).getName(),
                repositoryModels.get(position).getForksNumber(),
                repositoryModels.get(position).getStarsNumber(),
                repositoryModels.get(position).getLanguage()
        );
        holder.binding.setRepositoryModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return repositoryModels.size();
    }

    public class RepositoryHolder extends RecyclerView.ViewHolder {
        RepositoryListItemBinding binding;

        public RepositoryHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public void setData(List<RepositoryModel> repositoryModels) {
        this.repositoryModels = repositoryModels;
        notifyDataSetChanged();
    }

    public class RepositoryItemViewModel {
        private String repositoryName;
        private String forksNumber;
        private String starsNumber;
        private String language;

        public RepositoryItemViewModel(String repositoryName, int forksNumber, int starsNumber, String language) {
            this.repositoryName = repositoryName;
            setForksNumber(forksNumber);
            setStarsNumber(starsNumber);
            setLanguage(language);
        }

        public void setRepositoryName(String repositoryName) {
            this.repositoryName = repositoryName;
        }

        public void setForksNumber(int forksNumber) {
            this.forksNumber = Integer.toString(forksNumber);
        }

        public void setStarsNumber(int starsNumber) {
            this.starsNumber = Integer.toString(starsNumber);
        }

        public void setLanguage(String language) {
            this.language = context.getString(R.string.language, language);
        }

        public String getRepositoryName() {
            return repositoryName;
        }

        public String getForksNumber() {
            return forksNumber;
        }

        public String getStarsNumber() {
            return starsNumber;
        }

        public String getLanguage() {
            return language;
        }
    }
}
