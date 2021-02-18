package com.silva021.covid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CovidData {
    @JsonIgnore
    public static final String BASE_URL = "https://covid19-brazil-api.now.sh/api/report/v1/";

    private float uid;
    private String uf;
    private String state;
    private int cases;
    private int deaths;
    private int suspects;
    private int refuses;
    private String datetime;
    private boolean broadcast;
    private String comments;


    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "CovidData{" +
                "uid=" + uid +
                ", uf='" + uf + '\'' +
                ", state='" + state + '\'' +
                ", cases=" + cases +
                ", deaths=" + deaths +
                ", suspects=" + suspects +
                ", refuses=" + refuses +
                ", datetime='" + datetime + '\'' +
                '}';
    }

    public float getUid() {
        return uid;
    }

    public void setUid(float uid) {
        this.uid = uid;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public float getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public float getSuspects() {
        return suspects;
    }

    public void setSuspects(int suspects) {
        this.suspects = suspects;
    }

    public float getRefuses() {
        return refuses;
    }

    public void setRefuses(int refuses) {
        this.refuses = refuses;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
