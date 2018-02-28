package com.gooner10.weatherforecast.weather;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gooner10.weatherforecast.Network.VolleySingleton;

import org.json.JSONObject;

/**
 * WeatherService to call weather Api using volley
 */
public class ApiWeatherService implements WeatherService {
    public static final String LOG_TAG = "ApiWeatherService";

    @Override
    public void getWeatherData(final Callback callback) {

        final String url = "https://api.forecast.io/forecast/203bf0976335ed98863b556ed9f61f79/38.968,-76.873";
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(LOG_TAG, "response" + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error.getMessage());
            }
        });

        requestQueue.add(jsObjRequest);
    }
}
