package com.mlsdev.serhiy.domain.interactor.abstraction;

import rx.Subscriber;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public interface GetFollowersUseCase {
    /**
     * Execute this use case
     *
     * @param userName The username is for the searching and retrieving user's followers;
     */
    void execute(String userName, Subscriber<Integer> subscriber);

    void unsubscribe();
}
