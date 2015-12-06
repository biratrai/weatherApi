package com.gooner10.weatherforecast;

import org.json.JSONObject;

public interface ParseResponseCallback {
    void onSuccess(JSONObject response);

    void onError(String errorMessage);
}
