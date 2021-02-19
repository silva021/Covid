package com.silva021.covid.main;

import android.util.Log;

import com.silva021.covid.API.RetrofitConfiguration;
import com.silva021.covid.domain.CovidAPI;
import com.silva021.covid.model.Covid;
import com.silva021.covid.model.CovidBrazil;
import com.silva021.covid.model.CovidData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View mMainContract;
    CovidAPI covidAPI;
    Retrofit retrofit;

    public MainPresenter(MainContract.View mMainContract) {
        this.mMainContract = mMainContract;
        mMainContract.setPresenter(this);
        retrofit = new RetrofitConfiguration(CovidData.BASE_URL).getInstance();
        covidAPI = retrofit.create(CovidAPI.class);
    }

    @Override
    public void loadCovidDataBrazil() {
        covidAPI.getCovidDataBrazil().enqueue(new Callback<CovidBrazil>() {
            @Override
            public void onResponse(Call<CovidBrazil> call, Response<CovidBrazil> response) {
                CovidData covidBrazil = response.body().getData();
                covidBrazil.setState("Brasil");
                mMainContract.updateCovidData(covidBrazil);
            }

            @Override
            public void onFailure(Call<CovidBrazil> call, Throwable t) {
                Log.d("covid", t.getMessage());
            }
        });

    }

    @Override
    public void loadCovidDataAllBrazilUF(String date) {
        covidAPI.getCovidDataDate(date).enqueue(new Callback<Covid>() {
            @Override
            public void onResponse(Call<Covid> call, Response<Covid> response) {
                mMainContract.initializeRecycler(response.body().getData());
            }

            @Override
            public void onFailure(Call<Covid> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadCovidStateUF(String uf) {
        covidAPI.getCovidDataUF(uf).enqueue(new Callback<CovidData>() {
            @Override
            public void onResponse(Call<CovidData> call, Response<CovidData> response) {
                mMainContract.updateCovidData(response.body());
            }

            @Override
            public void onFailure(Call<CovidData> call, Throwable t) {
                Log.d("covid", t.getMessage());
            }
        });
    }

    @Override
    public void start() {
        loadCovidDataBrazil();
        loadCovidDataAllBrazilUF("20200317");
    }
}
