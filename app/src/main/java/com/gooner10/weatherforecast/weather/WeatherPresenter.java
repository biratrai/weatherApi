package com.gooner10.weatherforecast.weather;

import com.google.gson.Gson;
import com.gooner10.weatherforecast.Model.ForeCastApiModel;

import org.json.JSONObject;

/**
 * Presenter for WeatherView
 */
public class WeatherPresenter implements WeatherContract.userActions {

    private WeatherService wService;
    private WeatherContract.view wView;

    public WeatherPresenter(WeatherService wService, WeatherContract.view wView) {
        this.wService = wService;
        this.wView = wView;
    }

    @Override
    public void loadData() {
        wService.getWeatherData(new Callback() {
            @Override
            public void onSuccess(JSONObject response) {
                ForeCastApiModel model = parseJSONresponse(response);
                wView.displayTodaysTemperature(model);
                wView.displayWeeklyWeatherData(model.getDaily().getData());
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    @Override
    public void userClicksMoreInfoOnTodaysTemperature() {

    }


    private ForeCastApiModel parseJSONresponse(JSONObject response) {
        Gson gson = new Gson();
        return gson.fromJson(response.toString(), ForeCastApiModel.class);
    }
}
