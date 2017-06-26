package com.example.napoleonkaiser.mvpexample;

/**
 * Created by napoleonkaiser on 19/06/2017.
 */

public interface MainPresenter {
    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}
