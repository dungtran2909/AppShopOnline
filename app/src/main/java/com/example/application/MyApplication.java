package com.example.application;

import android.app.Application;

import com.example.network.ApiService;
import com.example.sharedpreferences.SharedPreferencesManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.init(this);
        ApiService.init("http://apiuimihn.somee.com/api/");
    }
}
