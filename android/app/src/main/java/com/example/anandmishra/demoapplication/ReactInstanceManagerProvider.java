package com.example.anandmishra.demoapplication;

import android.app.Application;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;

/**
 * Created by anandmishra on 12/01/18.
 */

class ReactInstanceManagerProvider {

    private static ReactInstanceManager reactInstanceManager = null;

    public static ReactInstanceManager getInstance(Application application) {
        if (reactInstanceManager == null) {
            synchronized (ReactInstanceManagerProvider.class) {
                if (reactInstanceManager == null) {
                    reactInstanceManager = ReactInstanceManager.builder()
                            .setApplication(application)
                            .setBundleAssetName("index.android.bundle")
                            .setJSMainModulePath("index")
                            .addPackage(new MainReactPackage())
                            .addPackage(new ToastModulePackager())
                            .addPackage(new ProgressBarPackager())
                            .setUseDeveloperSupport(BuildConfig.DEBUG)
                            .setInitialLifecycleState(LifecycleState.RESUMED)
                            .build();
                    return reactInstanceManager;
                }
            }
        }
        return reactInstanceManager;
    }
}