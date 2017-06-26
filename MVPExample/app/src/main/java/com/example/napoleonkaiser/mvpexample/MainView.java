package com.example.napoleonkaiser.mvpexample;


import io.realm.Realm;

/**
 * Created by napoleonkaiser on 19/06/2017.
 */

public interface MainView {
    void showProgress();

    void hideProgress();

    void setItem(Realm realm);

    void showMessage(String message);
}
