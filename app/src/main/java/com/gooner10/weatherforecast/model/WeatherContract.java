package com.gooner10.weatherforecast.model;

import com.gooner10.weatherforecast.model.pojo.DailyTemp;
import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;

import java.util.List;

public interface WeatherContract {
    interface view {
        void displayTodayWeather(ForeCastApiModel apiModel);

        void displayWeeklyWeather(List<DailyTemp> dailyTemps);
    }

    interface userActions {

        void loadData();

        void clickForDetailTodayWeather();

    }
}
