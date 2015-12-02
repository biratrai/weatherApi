package com.gooner10.weatherforecast.Network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.gooner10.weatherforecast.Extras.MyApplication;


public class VolleySingleton {

    private static VolleySingleton sInstance;

    private static RequestQueue mRequestQueue;

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getInstance() {
        if (sInstance == null) {
            sInstance = new VolleySingleton();
        }

        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
