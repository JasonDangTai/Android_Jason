package com.example.napoleonkaiser.foodapplication.module;

/**
 * Created by napoleonkaiser on 16/06/2017.
 */

public class Data {
    private String text1;
    private String text2;
    private String image;

    public Data(String text1, String text2, String image) {
        this.text1 = text1;
        this.text2 = text2;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text) {
        this.text1 = text;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText2() {
        return text2;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
