package com.silva021.covid.Main;

import com.silva021.covid.base.BasePresenter;
import com.silva021.covid.base.BaseView;
import com.silva021.covid.model.CovidData;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void notifyUserFailureGet(String message);
        void updateCovidData(CovidData covidData);
    }

    interface Presenter extends BasePresenter {
        void loadCovidState();
        void loadCovidStateUF(String uf);
    }
}
