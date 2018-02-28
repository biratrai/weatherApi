package com.gooner10.weatherforecast.weather;


import com.gooner10.weatherforecast.Model.pojo.DailyTemp;
import com.gooner10.weatherforecast.Model.pojo.ForeCastApiModel;

import java.util.List;

/**
 * Contract for Weather
 */
public interface WeatherContract {

    interface view {

        void displayTodaysTemperature(ForeCastApiModel data);

        void displayWeeklyWeatherData(List<DailyTemp> weeklyTemperature);
    }

    interface userActions {

        void loadData();

        void userClicksMoreInfoOnTodaysTemperature();

    }
}
