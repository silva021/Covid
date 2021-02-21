package com.silva021.covid.locationFilter;

import com.silva021.covid.base.BasePresenter;
import com.silva021.covid.base.BaseView;
import com.silva021.covid.model.Location;

import java.util.List;

public interface LocationFilterContract {
    interface View extends BaseView<Presenter> {
        void notifyUserFailureGet(String message);
        void initalizeRecycler(List<Location> list);
        void showProgress(boolean b);
    }

    interface Presenter extends BasePresenter {
        void loadListUF();
    }

}
