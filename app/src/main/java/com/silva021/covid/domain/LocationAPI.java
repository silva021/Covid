package com.silva021.covid.domain;

import com.silva021.covid.model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationAPI {
    @GET("estados/")
    Call<List<Location>> getAllUF();
}