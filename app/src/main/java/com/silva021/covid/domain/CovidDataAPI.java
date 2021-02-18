package com.silva021.covid.domain;

import com.silva021.covid.model.Covid;
import com.silva021.covid.model.CovidData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidDataAPI {

    @GET("brazil")
    Call<Covid> getCovidDataBrazil();

    @GET("brazil/uf/{uf}")
    Call<CovidData> getCovidDataUF(@Path("uf") String uf);
}
