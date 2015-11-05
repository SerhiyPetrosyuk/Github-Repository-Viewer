package com.mlsdev.serhiy.domain.interactor.abstraction;

/**
 * Created by serhiy on 05.11.15.
 */
public abstract class BaseSubscriber<T> extends rx.Subscriber<T>{

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
