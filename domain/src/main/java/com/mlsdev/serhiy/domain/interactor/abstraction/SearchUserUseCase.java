package com.mlsdev.serhiy.domain.interactor.abstraction;

import com.mlsdev.serhiy.domain.model.GithubUser;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public interface SearchUserUseCase {

    /**
     * Callback used to be notified when either a user has been loaded or an error happened.
     */
    public interface Callback {
        void onUserDataLoaded(GithubUser githubUser);
        void onError();
    }

    /**
     * Execute this use case
     *
     * @param searchedName The searched name is for the searching and retrieving data;
     * @param callback {@link Callback} is for notifying a client.
     */
    public void execute(String searchedName, Callback callback);

}
