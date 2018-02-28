package com.gooner10.weatherforecast.Services;

import org.json.JSONObject;

public interface ParseResponseCallback {
    void onSuccess(JSONObject response);

    void onError(String errorMessage);
}
