package com.mlsdev.serhiy.domain.interactor.abstraction;

/**
 * Created by serhiy on 03.11.15.
 */
public interface InteractorCallback<T> {
    void onSuccess(T data);
    void onError(String errorMessage);
}
