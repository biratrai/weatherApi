package com.gooner10.weatherforecast.presenters;

import android.util.Log;

import com.google.gson.Gson;
import com.gooner10.weatherforecast.model.WeatherContract;
import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;
import com.gooner10.weatherforecast.services.ParseResponseCallback;
import com.gooner10.weatherforecast.services.WeatherService;

import org.json.JSONObject;

public class WeatherPresenter implements WeatherContract.userActions {
    public static final String TAG = WeatherPresenter.class.getSimpleName();
    private final WeatherService weatherService;
    private final WeatherContract.view wView;

    public WeatherPresenter(WeatherService weatherService, WeatherContract.view wView) {
        this.weatherService = weatherService;
        this.wView = wView;
    }

    @Override
    public void loadData() {
        weatherService.getWeatherData(new ParseResponseCallback() {
            @Override
            public void onSuccess(JSONObject response) {
                ForeCastApiModel apiModel = parseJSONResponse(response);
                wView.displayTodayWeather(apiModel);
                wView.displayWeeklyWeather(apiModel.getDaily().getData());
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, "onError: " + errorMessage);
            }
        });
    }

    private ForeCastApiModel parseJSONResponse(JSONObject response) {
        Gson gson = new Gson();
        return gson.fromJson(response.toString(), ForeCastApiModel.class);
    }

    @Override
    public void clickForDetailTodayWeather() {

    }
}
