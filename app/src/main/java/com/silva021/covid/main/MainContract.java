package com.silva021.covid.main;

import com.silva021.covid.base.BasePresenter;
import com.silva021.covid.base.BaseView;
import com.silva021.covid.model.CovidData;

import java.util.ArrayList;
import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void notifyUserFailureGet(String message);
        void initializeRecycler(ArrayList<CovidData> covidData);
        void updateCovidData(CovidData covidData);
    }

    interface Presenter extends BasePresenter {
        void loadCovidDataBrazil();
        void loadCovidDataAllBrazilUF(String date);
        void loadCovidStateUF(String uf);
    }
}
