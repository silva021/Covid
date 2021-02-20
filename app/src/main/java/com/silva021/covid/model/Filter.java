package com.silva021.covid.model;

import java.io.Serializable;

public class Filter implements Serializable {
    private String date;
    private Location location;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
