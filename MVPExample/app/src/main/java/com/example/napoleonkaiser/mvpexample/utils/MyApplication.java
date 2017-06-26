package com.example.napoleonkaiser.mvpexample.utils;

import android.app.Application;

import com.example.napoleonkaiser.mvpexample.Data.Book;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;

/**
 * Created by napoleonkaiser on 23/06/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);


        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
