package com.example.napoleonkaiser.mvpexample;

import io.realm.Realm;

/**
 * Created by napoleonkaiser on 19/06/2017.
 */

public interface MainInteractor {
    interface OnFinishedListener{
        void onFinished(Realm realm);
    }
    void findItems(OnFinishedListener listener);
}
