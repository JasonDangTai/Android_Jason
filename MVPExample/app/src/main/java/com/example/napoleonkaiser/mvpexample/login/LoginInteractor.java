package com.example.napoleonkaiser.mvpexample.login;

/**
 * Created by napoleonkaiser on 20/06/2017.
 */

public interface LoginInteractor {
    interface OnLoginFinishListener {
        void onUsernameError();
        void onPasswordError();
        void onSuccess();

    }

    void login(String username, String password, OnLoginFinishListener listener);
}
