package com.silva021.covid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CovidData {
    public static final String BASE_URL = "https://covid19-brazil-api.now.sh/api/report/v1/";

    @SerializedName("uid")
    @Expose
    private int uid;
    @SerializedName("uf")
    @Expose
    private String uf;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("cases")
    @Expose
    private float cases;
    @SerializedName("deaths")
    @Expose
    private float deaths;
    @SerializedName("suspects")
    @Expose
    private float suspects;
    @SerializedName("refuses")
    @Expose
    private float refuses;
    @SerializedName("datetime")
    @Expose
    private String datetime;


    @SerializedName("broadcast")
    @Expose
    private boolean broadcast;

    @SerializedName("comments")
    @Expose
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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
                ", broadcast=" + broadcast +
                ", comments='" + comments + '\'' +
                '}';
    }
}
