package com.yujun.asyncloadingusingvolley;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 于军 on 2016/8/21.
 */
public class MyApplication extends Application {

    private static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueue() {
        return queue;
    }
}
