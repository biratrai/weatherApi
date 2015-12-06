package com.gooner10.weatherforecast;

import com.google.gson.Gson;
import com.gooner10.weatherforecast.Model.ForeCastApiModel;

import org.json.JSONObject;

public class WeatherPresenter implements WeatherContract.userActions {
    WeatherService weatherService;
    WeatherContract.view wView;

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
