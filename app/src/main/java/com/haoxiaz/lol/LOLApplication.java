package com.haoxiaz.lol;

import android.app.Application;
import android.util.Log;

/**
 * Created by haoxiaz on 4/22/16.
 */
public class LOLApplication extends Application {

    private static String LOG_TAG = LOLApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(LOG_TAG, "Application started");
    }
}
