package com.mlsdev.serhiy.domain.interactor.abstraction;

import com.mlsdev.serhiy.domain.model.GithubRepository;

import java.util.Collection;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public interface SearchRepositoryUseCase {

    /**
     * Callback used to be notified when either a repositories have been loaded or an error happened.
     */
    public interface Callback {
        void onUserDataLoaded(Collection<GithubRepository> repositories);
        void onError();
    }

    /**
     * Execute this use case
     *
     * @param repositoriesUrl The repositories url is for the retrieving data;
     * @param callback {@link Callback} is for notifying a client.
     */
    public void execute(String repositoriesUrl, Callback callback);

}
