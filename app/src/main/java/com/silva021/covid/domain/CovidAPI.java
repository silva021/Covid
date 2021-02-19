package com.silva021.covid.domain;

import com.silva021.covid.model.Covid;
import com.silva021.covid.model.CovidBrazil;
import com.silva021.covid.model.CovidData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidAPI {

    @GET("brazil")
    Call<CovidBrazil> getCovidDataBrazil();

    @GET("brazil/uf/{uf}")
    Call<CovidData> getCovidDataUF(@Path("uf") String uf);

    @GET("brazil/{date}")
    Call<Covid> getCovidDataDate(@Path("date") String date);
}
