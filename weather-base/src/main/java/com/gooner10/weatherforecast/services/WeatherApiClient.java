package com.gooner10.weatherforecast.services;

import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;

import retrofit2.http.GET;

/**
 * Retrofit client for weather api
 */

public interface WeatherApiClient {
    @GET()
    ForeCastApiModel getWeatherData();
}
