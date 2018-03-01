package com.gooner10.weatherforecast.services;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gooner10.weatherforecast.services.network.VolleySingleton;

import org.json.JSONObject;

public class ApiWeatherService implements WeatherService {
    private static final String LOG_TAG = ApiWeatherService.class.getSimpleName();
    private static final String url = "https://api.forecast.io/forecast/203bf0976335ed98863b556ed9f61f79/38.968,-76.873";

    @Override
    public void getWeatherData(final ParseResponseCallback responseCallback) {
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(LOG_TAG, "response" + response);
                responseCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseCallback.onError(error.getMessage());
            }
        });

        requestQueue.add(jsObjRequest);
    }
}
