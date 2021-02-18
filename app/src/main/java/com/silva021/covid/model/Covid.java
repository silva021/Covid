package com.silva021.covid.model;

public class Covid {
    CovidData covidData;

    public CovidData getDadosCovid() {
        return covidData;
    }

    public void setDadosCovid(CovidData dadosCovidObject) {
        this.covidData = dadosCovidObject;
    }

    @Override
    public String toString() {
        return "Covid{" +
                "data=" + covidData +
                '}';
    }
}
