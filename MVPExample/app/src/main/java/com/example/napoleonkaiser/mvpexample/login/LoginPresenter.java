package com.example.napoleonkaiser.mvpexample.login;

/**
 * Created by napoleonkaiser on 20/06/2017.
 */

public interface LoginPresenter {
    void validate(String username, String password);

    void onDestroy();
}
