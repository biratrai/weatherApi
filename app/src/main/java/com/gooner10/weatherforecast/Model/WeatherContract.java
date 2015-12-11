package com.gooner10.weatherforecast.Model;

import com.gooner10.weatherforecast.Model.pojo.DailyTemp;
import com.gooner10.weatherforecast.Model.pojo.ForeCastApiModel;

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
