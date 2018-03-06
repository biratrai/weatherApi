package com.gooner10.weatherforecast.network;

import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Retrofit client for weather api
 */

public interface WeatherApiClient {
    @GET("{location}")
    Call<ForeCastApiModel> getWeatherData(@Path("location") String location);
}
