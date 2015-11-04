package com.mlsdev.serhiy.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mlsdev.serhiy.data.entity.follows.Followers;
import com.mlsdev.serhiy.data.entity.follows.Following;
import com.mlsdev.serhiy.data.entity.repository.RepositoryEntity;
import com.mlsdev.serhiy.data.entity.user.SearchUserResult;

import java.lang.reflect.Type;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

/**
 * Class is used to transform from Strings representing json to valid objects.
 */
@Singleton
public class EntityJsonMapper {

    private Gson gson;

    @Inject
    public EntityJsonMapper() {
        gson = new Gson();
    }

    /**
     * Transforms from a valid json into {@link Followers}
     * @param jsonGetFollowerResponse A json representation of followers
     * @return {@link Followers}
     * @throws com.google.gson.JsonSyntaxException if a json string isn't a valid json structure.
     * */
    public Followers transformFollowers(String jsonGetFollowerResponse) throws JsonSyntaxException {

        try {
            Followers followers = gson.fromJson(jsonGetFollowerResponse, Followers.class);

            return followers;
        } catch (JsonSyntaxException exception){
            throw exception;
        }

    }

    /**
     * Transforms from a valid json into {@link Followers}
     * @param jsonGetFollowingResponse A json representation of followers
     * @return {@link Followers}
     * @throws com.google.gson.JsonSyntaxException if a json string isn't a valid json structure.
     * */
    public Following transformFollowings(String jsonGetFollowingResponse) throws JsonSyntaxException {

        try {
            Following following = gson.fromJson(jsonGetFollowingResponse, Following.class);

            return following;
        } catch (JsonSyntaxException exception){
            throw exception;
        }

    }

    /**
     * Transforms from a valid json into {@link java.util.Collection<RepositoryEntity>}
     * @param jsonSearchReposResponse A json representation of a repository collection
     * @return {@link java.util.Collection<RepositoryEntity>}
     * @throws com.google.gson.JsonSyntaxException if a json string isn't a valid json structure.
     * */
    public Collection<RepositoryEntity> transformRepositoryCollection(String jsonSearchReposResponse) throws JsonSyntaxException {

        try {
            Type collectionType = new TypeToken<Collection<RepositoryEntity>>(){}.getType();
            Collection<RepositoryEntity> repositoryCollection = gson.fromJson(jsonSearchReposResponse, collectionType);
            return repositoryCollection;
        } catch (JsonSyntaxException exception){
            throw exception;
        }

    }

    /**
     * Transform from a valid json into {@link com.mlsdev.serhiy.data.entity.user.Item}
     *
     * @param searchUserJsonResponse A JSON representing of a user profile
     * @return {@link com.mlsdev.serhiy.data.entity.user.Item}
     * @throws com.google.gson.JsonSyntaxException if a json string isn't a valid json structure.
     * */
    public com.mlsdev.serhiy.data.entity.user.Item transformUser(String searchUserJsonResponse) throws JsonSyntaxException{
        try {
            SearchUserResult searchUserResult = gson.fromJson(searchUserJsonResponse, SearchUserResult.class);

            if (searchUserResult.getItems().size() > 0)
                return searchUserResult.getItems().get(0);
            else
                return null;

        } catch (JsonSyntaxException exception){
            throw exception;
        }
    }

}
