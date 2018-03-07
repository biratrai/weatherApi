package com.gooner10.weatherforecast.weatherhome;

import android.util.Log;

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
    public void loadData(String locationCoordinate) {
        WeatherApiClient foreCastApiModel = ServiceGenerator.createService(WeatherApiClient.class);
        Call<ForeCastApiModel> call = foreCastApiModel.getWeatherData(locationCoordinate);
        call.enqueue(new Callback<ForeCastApiModel>() {
            @Override
            public void onResponse(Call<ForeCastApiModel> call, Response<ForeCastApiModel> response) {
                Log.i(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    ForeCastApiModel apiModel = response.body();
                    weatherView.displayTodaysTemperature(apiModel);
                    weatherView.displayWeeklyWeatherData(apiModel.getDaily().getData());
                }
            }

            @Override
            public void onFailure(Call<ForeCastApiModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void userClicksMoreInfoOnTodaysTemperature() {

    }
}
