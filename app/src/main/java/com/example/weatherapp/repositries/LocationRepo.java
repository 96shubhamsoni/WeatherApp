package com.example.weatherapp.repositries;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.weatherapp.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

public class LocationRepo {
    private static final String TAG = "LocationRepo";
    public FusedLocationProviderClient mFusedLocationClient;
    public Activity activity;
    public String city="demo";

    public LocationRepo(Activity activity) {
        this.activity = activity;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    @SuppressLint("MissingPermission")
    public void detectCity() {
        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location == null) {
                    requestNewLocationData();
                } else {
                    getCity(location.getLatitude(), location.getLongitude());
                    // Log.d(TAG, City[0] +" 2");
                }
            }
        });
    }

    public void getCity(double lat, double lon) {
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocation(lat, lon, 1);
            if (addressList.size() > 0) {
                city = addressList.get(0).getLocality();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Log.d(TAG, city);
    }

    @SuppressLint("MissingPermission")
    public void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    public final com.google.android.gms.location.LocationCallback mLocationCallback = new com.google.android.gms.location.LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            getCity(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
    };

    public String getCityName(){
        return city;
    }
}
