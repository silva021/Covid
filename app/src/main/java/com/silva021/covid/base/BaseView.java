package com.silva021.covid.base;

import androidx.annotation.NonNull;

public interface BaseView<T> {
    void setPresenter(@NonNull T presenter);
}

