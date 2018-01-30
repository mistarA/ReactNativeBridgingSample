package com.example.anandmishra.demoapplication;

import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.image.ReactImageView;

/**
 * Created by anandmishra on 15/01/18.
 */

public class ProgressBarViewManager extends SimpleViewManager<ProgressBar> {

    public static final String REACT_CLASS = "ProgressBar";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ProgressBar createViewInstance(ThemedReactContext reactContext) {
        return new ProgressBar(reactContext);
    }

    @ReactProp(name = "progress", defaultInt = 0)
    public void setProgress(ProgressBar view, int progress) {
        view.setProgress(progress);
    }

    @ReactProp(name ="indeterminate", defaultBoolean = false)
    public void setIndeterminate(ProgressBar view, boolean indeterminate) {
        view.setIndeterminate(indeterminate);
    }
}
