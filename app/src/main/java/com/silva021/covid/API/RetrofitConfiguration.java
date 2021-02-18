package com.silva021.covid.API;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfiguration {
    private String base_url;

    public RetrofitConfiguration(String base_url) {
        this.base_url = base_url;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl(this.base_url)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
