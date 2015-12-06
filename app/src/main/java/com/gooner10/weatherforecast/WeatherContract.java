package com.gooner10.weatherforecast;

import com.gooner10.weatherforecast.Model.DailyTemp;
import com.gooner10.weatherforecast.Model.ForeCastApiModel;

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
