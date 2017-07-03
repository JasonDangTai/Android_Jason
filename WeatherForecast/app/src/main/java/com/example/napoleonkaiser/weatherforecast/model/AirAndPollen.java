
package com.example.napoleonkaiser.weatherforecast.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirAndPollen {

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Category")
    @Expose
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
