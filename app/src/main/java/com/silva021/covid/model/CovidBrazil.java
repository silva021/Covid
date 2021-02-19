package com.silva021.covid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CovidBrazil {
    @SerializedName("data")
    @Expose
    private CovidData data = null;

    public CovidData getData() {
        return data;
    }

    public void setData(CovidData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Covid{" +
                "data=" + data +
                '}';
    }
}
