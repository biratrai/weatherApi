package com.gooner10.weatherforecast.presenters;

import android.util.Log;

import com.gooner10.weatherforecast.model.WeatherContract;
import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;
import com.gooner10.weatherforecast.network.ServiceGenerator;
import com.gooner10.weatherforecast.network.WeatherApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.userActions {
    public static final String TAG = WeatherPresenter.class.getSimpleName();
    private final WeatherContract.view weatherView;

    public WeatherPresenter(WeatherContract.view view) {
        this.weatherView = view;
    }

    @Override
    public void loadData() {
        WeatherApiClient foreCastApiModel = ServiceGenerator.createService(WeatherApiClient.class);
        Call<ForeCastApiModel> call = foreCastApiModel.getWeatherData("38.968,-76.873");
        call.enqueue(new Callback<ForeCastApiModel>() {
            @Override
            public void onResponse(Call<ForeCastApiModel> call, Response<ForeCastApiModel> response) {
                Log.i(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    ForeCastApiModel apiModel = response.body();
                    weatherView.displayTodayWeather(apiModel);
                    weatherView.displayWeeklyWeather(apiModel.getDaily().getData());
                }
            }

            @Override
            public void onFailure(Call<ForeCastApiModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void clickForDetailTodayWeather() {

    }
}
