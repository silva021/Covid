package com.silva021.covid.main;

import android.util.Log;

import com.silva021.covid.API.RetrofitConfiguration;
import com.silva021.covid.domain.CovidAPI;
import com.silva021.covid.model.Covid;
import com.silva021.covid.model.CovidBrazil;
import com.silva021.covid.model.CovidData;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View mMainView;
    CovidAPI covidAPI;
    Retrofit retrofit;

    public MainPresenter(MainContract.View mMainView) {
        this.mMainView = mMainView;
        mMainView.setPresenter(this);
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
                mMainView.updateViewCovidDataCountry(covidBrazil);
                mMainView.showProgressLiveData(false);
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
                if (response.body().getData().isEmpty())
                    mMainView.notifyUserCovidDataEmply("Não tem dados atualizado para data de hoje.");
                else
                    mMainView.initializeRecycler(response.body().getData());

                mMainView.showProgressRecycler(false);
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
                mMainView.updateViewCovidDataUF(response.body());
                mMainView.showProgressLiveData(false);
            }

            @Override
            public void onFailure(Call<CovidData> call, Throwable t) {
                Log.d("covid", t.getMessage());
            }
        });
    }

    @Override
    public void start() {
        Calendar c = Calendar.getInstance();
        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = (c.get(Calendar.MONTH) < 10 ? "0" + (c.get(Calendar.MONTH) + 1) : String.valueOf((c.get(Calendar.MONTH) + 1)));
        String day = c.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + c.get(Calendar.DAY_OF_MONTH) : String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        loadCovidDataBrazil();
        loadCovidDataAllBrazilUF(year + month + day);
    }
}
