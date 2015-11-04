package com.mlsdev.serhiy.data.entity.mapper;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.Item;
import com.mlsdev.serhiy.domain.model.GithubRepository;
import com.mlsdev.serhiy.domain.model.GithubUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class is for transforming an entity object into a domain.model
 * */
@Singleton
public class ModelEntityMapper {

    @Inject
    public ModelEntityMapper() {
    }

    /**
     * Transforms a {@link com.mlsdev.serhiy.data.entity.user.Item} into
     * a {@link GithubUser}
     * @param userEntity A {@link com.mlsdev.serhiy.data.entity.user.Item} is needed to represent
     * data like a {@link GithubUser}
     * @return {@link GithubUser}*/
    public GithubUser transformUserEntity(Item userEntity){
        GithubUser githubUser = null;

        if (userEntity != null){
            githubUser = new GithubUser();
            githubUser.setUserName(userEntity.getLogin());
            githubUser.setUserAvatar(userEntity.getAvatarUrl());
            githubUser.setUserProfileLink(userEntity.getHtmlUrl());
            githubUser.setmUserReposLink(userEntity.getReposUrl());
        }

        return githubUser;
    }

    /**
     * Transforms a {@link java.util.Collection<RepositoryEntity>} into
     * a {@link java.util.Collection<GithubRepository>}
     * @param repositoryEntities The collection of the {@link RepositoryEntity}
     * @return {@link java.util.Collection<GithubRepository>}
     * */
    public List<GithubRepository> transformRepositoryEntities(List<RepositoryEntity> repositoryEntities){
        List<GithubRepository> githubRepositoryCollection = new ArrayList<>();

        for (RepositoryEntity repositoryEntity : repositoryEntities) {
            GithubRepository githubRepository = new GithubRepository();
            githubRepository.setName(repositoryEntity.getName());
            githubRepository.setLanguage(repositoryEntity.getLanguage());
            githubRepository.setStarsNumber(repositoryEntity.getStargazersCount());
            githubRepository.setForksNumber(repositoryEntity.getForksCount());
            githubRepositoryCollection.add(githubRepository);
        }

        return githubRepositoryCollection;
    }

}
