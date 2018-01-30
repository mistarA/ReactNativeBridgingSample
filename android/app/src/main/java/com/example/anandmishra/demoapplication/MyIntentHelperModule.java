package com.example.anandmishra.demoapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by Aditya.K on 27/7/17.
 */

@ReactModule(name = MyIntentHelperModule.TAG)
public class MyIntentHelperModule extends ReactContextBaseJavaModule {

    static final String TAG = "MyIntentHelper";

    private static final String TELEPHONE = "tel:";
    private static final String SMS = "sms:";
    private static final String SMS_BODY = "sms_body";

    public MyIntentHelperModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return TAG;
    }

    @ReactMethod
    public void makePhoneCall(String phoneNumberString, Promise promise) {
        //Needs permission "android.permission.CALL_PHONE"
        Intent sendIntent = new Intent(Intent.ACTION_CALL, Uri.parse(TELEPHONE +
                phoneNumberString.replaceAll("#", "%23").trim()));
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Check that an app exists to receive the intent
        if (sendIntent.resolveActivity(getReactApplicationContext().getPackageManager()) != null) {
            try {
                getReactApplicationContext().startActivity(sendIntent);
                promise.resolve("Success");
            } catch (SecurityException ex) {
                //Logger.d(TAG, ex.getMessage());
                this.sendPhoneDial(phoneNumberString);
                promise.reject(ex);
            }
        } else {
            promise.reject("Error", "No apps found");
        }
    }

    @ReactMethod
    public void sendSms(String phoneNumberString, String body, Promise promise) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(SMS +
                phoneNumberString.trim()));
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (body != null) {
            sendIntent.putExtra(SMS_BODY, body);
        }

        //Check that an app exists to receive the intent
        if (sendIntent.resolveActivity(getReactApplicationContext().getPackageManager()) != null) {
            try {
                getReactApplicationContext().startActivity(sendIntent);
                promise.resolve("Success");
            } catch (Exception e) {
                promise.reject(e);
            }
        } else {
            promise.reject("Error", "No apps found");
        }
    }

    @ReactMethod
    public void launchGenericIntent(String url, Promise promise) {
        if (url == null || url.isEmpty()) {
            promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + url));
            return;
        }

        try {
            Activity currentActivity = getCurrentActivity();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if (intent.resolveActivity(getReactApplicationContext().getPackageManager()) != null) {
                String selfPackageName = getReactApplicationContext().getPackageName();
                ComponentName componentName = intent.resolveActivity(
                        getReactApplicationContext().getPackageManager());
                String otherPackageName = (componentName != null ? componentName.getPackageName() : "");

                // If there is no currentActivity or we are launching to a different package we need to set
                // the FLAG_ACTIVITY_NEW_TASK flag
                if (currentActivity == null || !selfPackageName.equals(otherPackageName)) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                if (currentActivity != null) {
                    currentActivity.startActivity(intent);
                } else {
                    getReactApplicationContext().startActivity(intent);
                }

                promise.resolve("Success");
            } else {
                promise.reject("Error", "No apps found");
            }
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException(
                    "Could not open URL '" + url + "': " + e.getMessage()));
        }
    }

    /*ACTION_SETTINGS
            ACTION_WIRELESS_SETTINGS
    ACTION_AIRPLANE_MODE_SETTINGS
            ACTION_WIFI_SETTINGS
    ACTION_APN_SETTINGS
            ACTION_BLUETOOTH_SETTINGS
    ACTION_DATE_SETTINGS
            ACTION_LOCALE_SETTINGS
    ACTION_INPUT_METHOD_SETTINGS
            ACTION_DISPLAY_SETTINGS
    ACTION_SECURITY_SETTINGS
            ACTION_LOCATION_SOURCE_SETTINGS
    ACTION_INTERNAL_STORAGE_SETTINGS
            ACTION_MEMORY_CARD_SETTINGS*/
    @ReactMethod
    public void launchSettingIntent(String intentName) {
        Intent intentCl = new Intent();
        switch (intentName) {
            case "ACTION_SETTINGS":
                intentCl.setAction(Settings.ACTION_SETTINGS);
                break;
            case "ACTION_WIRELESS_SETTINGS":
                intentCl.setAction(Settings.ACTION_WIRELESS_SETTINGS);
                break;
            case "ACTION_AIRPLANE_MODE_SETTINGS":
                intentCl.setAction(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                break;
            case "ACTION_WIFI_SETTINGS":
                intentCl.setAction(Settings.ACTION_WIFI_SETTINGS);
                break;
            case "ACTION_APN_SETTINGS":
                intentCl.setAction(Settings.ACTION_APN_SETTINGS);
                break;
            case "ACTION_BLUETOOTH_SETTINGS":
                intentCl.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
                break;
            case "ACTION_DATE_SETTINGS":
                intentCl.setAction(Settings.ACTION_DATE_SETTINGS);
                break;
            case "ACTION_LOCALE_SETTINGS":
                intentCl.setAction(Settings.ACTION_LOCALE_SETTINGS);
                break;
            case "ACTION_INPUT_METHOD_SETTINGS":
                intentCl.setAction(Settings.ACTION_INPUT_METHOD_SETTINGS);
                break;
            case "ACTION_DISPLAY_SETTINGS":
                intentCl.setAction(Settings.ACTION_DISPLAY_SETTINGS);
                break;
            case "ACTION_SECURITY_SETTINGS":
                intentCl.setAction(Settings.ACTION_SECURITY_SETTINGS);
                break;
            case "ACTION_LOCATION_SOURCE_SETTINGS":
                intentCl.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                break;
            case "ACTION_INTERNAL_STORAGE_SETTINGS":
                intentCl.setAction(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
                break;
            case "ACTION_MEMORY_CARD_SETTINGS":
                intentCl.setAction(Settings.ACTION_MEMORY_CARD_SETTINGS);
                break;
            case "ACTION_APPLICATION_DETAILS_SETTINGS":
                intentCl.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getReactApplicationContext().getPackageName(), null);
                intentCl.setData(uri);
                break;
            default:
                intentCl.setAction(Settings.ACTION_SETTINGS);
                break;
        }

        intentCl.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getReactApplicationContext().startActivity(intentCl);
    }


    private void sendPhoneDial(String phoneNumberString) {
        try {
            Intent sendIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(TELEPHONE +
                    phoneNumberString.trim()));
            sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Check that an app exists to receive the intent
            if (sendIntent.resolveActivity(getReactApplicationContext().getPackageManager()) != null) {
                getReactApplicationContext().startActivity(sendIntent);
            }
        } catch (Exception e) {

        }
    }

    // Used for launching intent using Intent Action
    //React Sdk version is 3
    @ReactMethod
    public void launchGenericSettingIntent(String intentName) {
        try {
            Activity currentActivity = getCurrentActivity();
            Intent intentCl = new Intent();
            intentCl.setAction(intentName);
            intentCl.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (currentActivity != null) {
                currentActivity.startActivity(intentCl);
            } else {
                getReactApplicationContext().startActivity(intentCl);
            }
        } catch (Exception e) {
            //Logger.e(TAG, e);
        }
    }

    //Used for launching intent using package name and class name
    //React Sdk version is 3
    @ReactMethod
    public void launchGenericSettingIntentWithComponent(String packageName, String cls) {
        try {
            Activity currentActivity = getCurrentActivity();
            Intent intentCl = new Intent();
            intentCl.setComponent(new ComponentName(packageName, cls));
            intentCl.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (currentActivity != null) {
                currentActivity.startActivity(intentCl);
            } else {
                getReactApplicationContext().startActivity(intentCl);
            }
        } catch (Exception e) {
            //Logger.e(TAG, e);
        }
    }

    @Override
    @Nullable
    public Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap<String, Object>();
        constants.put("ACTION_SETTINGS", "ACTION_SETTINGS");
        constants.put("ACTION_WIRELESS_SETTINGS", "ACTION_WIRELESS_SETTINGS");
        constants.put("ACTION_AIRPLANE_MODE_SETTINGS", "ACTION_AIRPLANE_MODE_SETTINGS");
        constants.put("ACTION_WIFI_SETTINGS", "ACTION_WIFI_SETTINGS");
        constants.put("ACTION_APN_SETTINGS", "ACTION_APN_SETTINGS");
        constants.put("ACTION_BLUETOOTH_SETTINGS", "ACTION_BLUETOOTH_SETTINGS");
        constants.put("ACTION_DATE_SETTINGS", "ACTION_DATE_SETTINGS");
        constants.put("ACTION_LOCALE_SETTINGS", "ACTION_LOCALE_SETTINGS");
        constants.put("ACTION_INPUT_METHOD_SETTINGS", "ACTION_INPUT_METHOD_SETTINGS");
        constants.put("ACTION_DISPLAY_SETTINGS", "ACTION_DISPLAY_SETTINGS");
        constants.put("ACTION_SECURITY_SETTINGS", "ACTION_SECURITY_SETTINGS");
        constants.put("ACTION_LOCATION_SOURCE_SETTINGS", "ACTION_LOCATION_SOURCE_SETTINGS");
        constants.put("ACTION_INTERNAL_STORAGE_SETTINGS", "ACTION_INTERNAL_STORAGE_SETTINGS");
        constants.put("ACTION_MEMORY_CARD_SETTINGS", "ACTION_MEMORY_CARD_SETTINGS");
        constants.put("ACTION_APPLICATION_DETAILS_SETTINGS", "ACTION_APPLICATION_DETAILS_SETTINGS");
        return constants;
    }
}
