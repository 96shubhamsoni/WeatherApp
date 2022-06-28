package com.example.weatherapp;


import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;


public class WeatherData {
    @SerializedName("request")
    public JsonObject request;

    @SerializedName("location")
    public JsonObject location;

    @SerializedName("current")
    public JsonObject current;

    public JsonObject getRequest() {
        return request;
    }

    public JsonObject getLocation() {
        return location;
    }

    public JsonObject getCurrent() {
        return current;
    }
}
