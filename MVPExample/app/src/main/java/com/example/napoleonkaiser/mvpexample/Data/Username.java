package com.example.napoleonkaiser.mvpexample.Data;

import io.realm.RealmObject;

/**
 * Created by napoleonkaiser on 22/06/2017.
 */

public class Username extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
