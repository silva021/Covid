package com.silva021.covid.locationFilter;

import com.silva021.covid.API.RetrofitConfiguration;
import com.silva021.covid.domain.LocationAPI;
import com.silva021.covid.model.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.silva021.covid.model.Location.BASE_URL;

public class LocationFilterPresenter implements LocationFilterContract.Presenter {
    LocationFilterContract.View mLocationFilterView;

    public LocationFilterPresenter(LocationFilterContract.View mLocationFilterView) {
        this.mLocationFilterView = mLocationFilterView;
        mLocationFilterView.setPresenter(this);
    }

    @Override
    public void start() {
        loadListUF();
    }

    @Override
    public void loadListUF() {
        Retrofit instance = new RetrofitConfiguration(BASE_URL).getInstance();

        LocationAPI locationAPI = instance.create(LocationAPI.class);

        Call<List<Location>> allUF = locationAPI.getAllUF();

        allUF.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                mLocationFilterView.initalizeRecycler(response.body());
                mLocationFilterView.showProgress(false);
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                mLocationFilterView.notifyUserFailureGet("Ocorreu um erro ao retornar lista de Estados");
            }
        });
    }
}
