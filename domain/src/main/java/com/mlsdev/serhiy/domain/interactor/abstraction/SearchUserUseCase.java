package com.mlsdev.serhiy.domain.interactor.abstraction;

import com.mlsdev.serhiy.domain.model.GithubUser;

import rx.Subscriber;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public interface SearchUserUseCase {

    /**
     * Execute this use case
     *
     * @param searchedName The searched name is for the searching and retrieving data;
     */
    void execute(String searchedName, Subscriber<GithubUser> subscriber);

    void unsubscribe();
}
