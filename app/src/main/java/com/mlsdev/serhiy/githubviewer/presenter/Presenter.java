package com.mlsdev.serhiy.githubviewer.presenter;

/**
 * Created by Serhiy Petrosyuk on 20.04.15.
 */

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface Presenter {

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

}
