package com.example.weatherapp.mvvm;

import android.app.Activity;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CustomFactory implements ViewModelProvider.Factory {
    private Activity activity;


    public CustomFactory(Activity activity) {
       this.activity = activity;

    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new WeatherDataViewModel(activity);
    }
}
