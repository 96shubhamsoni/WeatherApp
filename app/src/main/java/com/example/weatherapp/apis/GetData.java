package com.example.weatherapp.apis;



import com.google.gson.JsonObject;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface GetData {

    String API_KEY="b8f92f8fad4811647a806aee77a978f3";

    @GET("/current?access_key="+API_KEY)
    Call<JsonObject> getWeatherData(@Query("query") String filters);
}
