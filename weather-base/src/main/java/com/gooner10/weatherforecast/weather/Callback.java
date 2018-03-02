package com.gooner10.weatherforecast.weather;

import org.json.JSONObject;

public interface Callback {
    void onSuccess(JSONObject response);

    void onError(String errorMessage);
}
