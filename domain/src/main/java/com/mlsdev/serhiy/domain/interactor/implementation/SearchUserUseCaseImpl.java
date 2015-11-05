package com.mlsdev.serhiy.domain.interactor.implementation;

import com.mlsdev.serhiy.domain.interactor.abstraction.SearchUserUseCase;
import com.mlsdev.serhiy.domain.model.GithubUser;
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
public class SearchUserUseCaseImpl implements SearchUserUseCase {
    private GithubUserRepository mGithubUserRepository;
    private Subscription subscription = Subscribers.empty();

    @Inject
    public SearchUserUseCaseImpl(GithubUserRepository githubUserRepository) {
        this.mGithubUserRepository = githubUserRepository;
    }

    @Override
    public void execute(String searchedName, Subscriber<GithubUser> subscriber) {
        subscription = mGithubUserRepository.searchGithubUserByName(searchedName)
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
