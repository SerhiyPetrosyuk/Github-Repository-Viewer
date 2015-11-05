package com.mlsdev.serhiy.domain.interactor.abstraction;

import com.mlsdev.serhiy.domain.model.GithubRepository;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public interface SearchRepositoryUseCase {

    /**
     * Execute this use case
     *
     * @param userName The user's name is for the retrieving data;
     */
    void execute(String userName, Subscriber<List<GithubRepository>> subscriber);

    void unsubscribe();
}
