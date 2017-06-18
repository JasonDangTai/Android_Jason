package com.example.napoleonkaiser.android_jason2.module;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by napoleonkaiser on 16/06/2017.
 */

public class Data {
    private String text;
    private String image;

    public Data(String text, String image) {
        this.text = text;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
