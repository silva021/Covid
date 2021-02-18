package com.silva021.covid.Main;

import android.util.Log;

import com.silva021.covid.API.RetrofitConfiguration;
import com.silva021.covid.domain.CovidDataAPI;
import com.silva021.covid.model.Covid;
import com.silva021.covid.model.CovidData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View mMainContract;
    CovidDataAPI covidDataAPI;
    Retrofit retrofit;

    public MainPresenter(MainContract.View mMainContract) {
        this.mMainContract = mMainContract;
        mMainContract.setPresenter(this);
        retrofit = new RetrofitConfiguration(CovidData.BASE_URL).getInstance();
        covidDataAPI = retrofit.create(CovidDataAPI.class);
    }

    @Override
    public void loadCovidState() {
        covidDataAPI.getCovidDataBrazil().enqueue(new Callback<Covid>() {
            @Override
            public void onResponse(Call<Covid> call, Response<Covid> response) {
                Log.d("covid", response.message());
            }

            @Override
            public void onFailure(Call<Covid> call, Throwable t) {
                Log.d("covid", t.getMessage());
            }
        });
    }

    @Override
    public void loadCovidStateUF(String uf) {
        covidDataAPI.getCovidDataUF(uf).enqueue(new Callback<CovidData>() {
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
    }
}
