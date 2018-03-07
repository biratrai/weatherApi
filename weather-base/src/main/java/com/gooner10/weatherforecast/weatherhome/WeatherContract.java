package com.gooner10.weatherforecast.weatherhome;


import com.gooner10.weatherforecast.model.pojo.DailyTemp;
import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;

import java.util.List;

/**
 * Contract for Weather
 */
public interface WeatherContract {

    interface view {

        void displayTodaysTemperature(ForeCastApiModel data);

        void displayWeeklyWeatherData(List<DailyTemp> weeklyTemperature);

        void loadData(String locationCoordinate);
    }

    interface userActions {

        void loadData(String locationCoordinate);

        void userClicksMoreInfoOnTodaysTemperature();

    }
}
