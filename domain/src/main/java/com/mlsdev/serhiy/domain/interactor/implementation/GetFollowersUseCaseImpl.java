package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.GetFollowersUseCase;
import com.mlsdev.serhiy.domain.repository.GithubUserRepository;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

/**
 * Created by Serhiy Petrosyuk on 17.04.15.
 */
public class GetFollowersUseCaseImpl implements GetFollowersUseCase {
    private GithubUserRepository mRepository;
    private Subscription subscription = Subscribers.empty();

    @Inject
    public GetFollowersUseCaseImpl(GithubUserRepository repository) {
        mRepository = repository;
    }

    @Override
    public void execute(String userName, Subscriber<Integer> subscriber) {
        subscription = mRepository.getFollowersNumber(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void unsubscribe() {
        if (!subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
