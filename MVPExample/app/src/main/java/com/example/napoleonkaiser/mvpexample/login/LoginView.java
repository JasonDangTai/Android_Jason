package com.example.napoleonkaiser.mvpexample.login;

/**
 * Created by napoleonkaiser on 20/06/2017.
 */

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}

