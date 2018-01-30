package com.example.anandmishra.demoapplication;

/**
 * Created by anandmishra on 12/01/18.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

public class ToastModule extends ReactContextBaseJavaModule {

    Context mContext;
    public SharedPreferences sharedPreferences;

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";

    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ToastExample";
    }

    @ReactMethod
    public void getDataKey() {
        try {
           /* Intent intent = new Intent(getReactApplicationContext(), MainActivity.class);
            getReactApplicationContext().startActivity(intent);*/
            Toast.makeText(getReactApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            //callback.invoke(sharedPreferences.getString("data_key", null));
        } catch (Exception e) {
            Toast.makeText(getReactApplicationContext(), "Failure in fetching data", Toast.LENGTH_SHORT).show();
        }
    }

    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }
}
