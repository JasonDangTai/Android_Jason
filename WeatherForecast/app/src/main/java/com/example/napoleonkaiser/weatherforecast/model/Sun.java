
package com.example.napoleonkaiser.weatherforecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sun {

    @SerializedName("Rise")
    @Expose
    private String rise;
    @SerializedName("Set")
    @Expose
    private String set;


    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

}
