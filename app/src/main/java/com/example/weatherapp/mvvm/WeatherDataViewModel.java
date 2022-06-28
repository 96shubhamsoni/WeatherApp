package com.example.weatherapp.mvvm;


import android.app.Activity;
import android.app.Application;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.repositries.WeatherRepo;
import com.google.gson.JsonObject;

public class WeatherDataViewModel extends ViewModel {
    private static final String TAG = "WeatherDataViewModel";
    public WeatherRepo weatherRepo = WeatherRepo.getInstance();;
    public MutableLiveData<JsonObject> weatherResponseLiveData;
    public Activity activity;

    public WeatherDataViewModel(Activity activity){
        super();
        this.activity = activity;
    }

    public void init(){

    }


    public LiveData<JsonObject> getWeather(String city){
        weatherRepo.fetchWeatherData(city);
        weatherResponseLiveData = weatherRepo.getWeatherData();
        return weatherResponseLiveData;
    }
}
