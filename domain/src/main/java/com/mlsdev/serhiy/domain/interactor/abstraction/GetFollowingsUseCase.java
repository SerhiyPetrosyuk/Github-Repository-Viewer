package com.mlsdev.serhiy.domain.interactor.abstraction;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public interface GetFollowingsUseCase {

    /**
     * Callback used to be notified when either a followings number has been loaded or an error happened.
     */
    public interface Callback {
        void onFollowingsLoaded(Integer followingsNumber);
        void onError();
    }

    /**
     * Execute this use case
     *
     * @param userName The username is for the searching and retrieving user's followings;
     * @param callback {@link Callback} is for notifying a client.
     */
    public void execute(String userName, InteractorCallback<Integer> callback);

}
