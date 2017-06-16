package com.example.napoleonkaiser.android_jason.moldule;

import java.io.Serializable;

/**
 * Created by napoleonkaiser on 13/06/2017.
 */

public class Food implements Serializable{
    private String name;
    private String img;


    public Food(String name, String img){
        this.name = name;
        this.img = img;
    }
    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }
}
