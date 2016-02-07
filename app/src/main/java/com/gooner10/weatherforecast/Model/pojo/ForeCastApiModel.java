package com.gooner10.weatherforecast.Model.pojo;

public class ForeCastApiModel {
    @Override
    public String toString() {
        return "ForeCastApiModel{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", timezone='" + timezone + '\'' +
                ", currently=" + currently +
                ", daily=" + daily +
                '}';
    }

    private String latitude;
    private String longitude;
    private String timezone;
    private CurrentForecast currently;
    private DailyForecast daily;

    public ForeCastApiModel(String latitude, String longitude, String timezone, CurrentForecast currently, DailyForecast daily) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.currently = currently;
        this.daily = daily;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public CurrentForecast getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentForecast currently) {
        this.currently = currently;
    }

    public DailyForecast getDaily() {
        return daily;
    }

    public void setDaily(DailyForecast daily) {
        this.daily = daily;
    }
}
