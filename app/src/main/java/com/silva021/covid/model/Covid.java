package com.silva021.covid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Covid {
    @SerializedName("data")
    @Expose
    private ArrayList<CovidData> data = null;

    public ArrayList<CovidData> getData() {
        return data;
    }

    public void setData(ArrayList<CovidData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Covid{" +
                "data=" + data +
                '}';
    }
}
