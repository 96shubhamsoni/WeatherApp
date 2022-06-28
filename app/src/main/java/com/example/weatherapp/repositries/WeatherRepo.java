package com.example.weatherapp.repositries;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.apis.GetData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepo {
    private static final String BASE_URL = "http://api.weatherstack.com";
    private static final String TAG = "WeatherRepo";
    private MutableLiveData<JsonObject> weatherReport;
    public GetData getDataService;

    private static final WeatherRepo weatherRepo = new WeatherRepo();

    public WeatherRepo() {
        weatherReport = new MutableLiveData<>();
        getDataService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GetData.class);
    }

    public static WeatherRepo getInstance() {
        return weatherRepo;
    }

    public void fetchWeatherData(String city) {
        getDataService.getWeatherData(city).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.body() != null) {
                    weatherReport.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                weatherReport.setValue(null);
            }
        });
    }

    public MutableLiveData<JsonObject> getWeatherData() {
        return weatherReport;
    }
}
