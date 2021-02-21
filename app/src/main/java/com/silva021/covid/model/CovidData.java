package com.silva021.covid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CovidData {
    public static final String BASE_URL = "https://covid19-brazil-api.now.sh/api/report/v1/";


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
    @SerializedName("confirmed")
    @Expose
    private long confirmed;
    @SerializedName("recovered")
    @Expose
    private long recovered;

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

    public void setCases(float cases) {
        this.cases = cases;
    }

    public void setDeaths(float deaths) {
        this.deaths = deaths;
    }

    public void setSuspects(float suspects) {
        this.suspects = suspects;
    }

    public void setRefuses(float refuses) {
        this.refuses = refuses;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "CovidData{" +
                ", uf='" + uf + '\'' +
                ", state='" + state + '\'' +
                ", cases=" + cases +
                ", deaths=" + deaths +
                ", suspects=" + suspects +
                ", refuses=" + refuses +
                ", datetime='" + datetime + '\'' +
                ", confirmed=" + confirmed +
                ", recovered=" + recovered +
                '}';
    }
}
