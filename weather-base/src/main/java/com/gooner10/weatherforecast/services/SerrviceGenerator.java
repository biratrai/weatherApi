package com.gooner10.weatherforecast.services;

import okhttp3.OkHttpClient;
import retrofit.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Service Generator for getting Network request
 */

public class SerrviceGenerator {
    private static final String API_BASE_URL = "https://api.forecast.io/forecast/203bf0976335ed98863b556ed9f61f79/";
    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
