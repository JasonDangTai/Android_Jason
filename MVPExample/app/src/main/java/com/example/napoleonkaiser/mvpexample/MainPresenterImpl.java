package com.example.napoleonkaiser.mvpexample;

/**
 * Created by napoleonkaiser on 19/06/2017.
 */


import io.realm.Realm;

public class MainPresenterImpl implements MainPresenter, MainInteractorImpl.OnFinishedListener {

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override public void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        mainInteractor.findItems(this);
    }

    @Override public void onItemClicked(int position) {
        if (mainView != null) {
            mainView.showMessage(String.format("Position %d clicked", position ));
        }
    }

    @Override public void onDestroy() {
        mainView = null;
    }

    @Override public void onFinished(Realm realm) {
        if (mainView != null) {
            mainView.setItem(realm);
            mainView.hideProgress();
        }
    }

    public MainView getMainView() {
        return mainView;
    }
}