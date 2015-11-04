package com.mlsdev.serhiy.githubviewer.model.mapper;

import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.githubviewer.model.RepositoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 22.04.15.
 */
@Singleton
public class RepositoryDataMapper {

    @Inject
    public RepositoryDataMapper() {
    }

    /**
     * Transforms a {@link GithubRepository} collection into a {@link RepositoryModel}.
     *
     * @param githubRepositories The {@link GithubRepository} collection to translate it into
     * a {@link RepositoryModel}.
     * @return a {@link RepositoryModel} collection.
     * */
    public List<RepositoryModel> transformRepositoryCollectionData(List<GithubRepository> githubRepositories){
        List<RepositoryModel> repositoryModels = new ArrayList<>();

        for (GithubRepository githubRepository : githubRepositories){
            RepositoryModel repositoryModel = new RepositoryModel();
            repositoryModel.setForksNumber(githubRepository.getForksNumber());
            repositoryModel.setLanguage(githubRepository.getLanguage());
            repositoryModel.setName(githubRepository.getName());
            repositoryModel.setStarsNumber(githubRepository.getStarsNumber());
            repositoryModels.add(repositoryModel);
        }

        return repositoryModels;
    }

}
