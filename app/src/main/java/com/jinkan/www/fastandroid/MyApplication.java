package com.jinkan.www.fastandroid;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sampson on 2018/4/17.
 * FastAndroid
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
