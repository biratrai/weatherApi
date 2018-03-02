package com.gooner10.weatherforecast.model.pojo;

public class CurrentForecast {
    private final String time;
    private final String summary;
    private final String icon;
    private final String nearestStormDistance;
    private final String precipIntensity;
    private final String precipIntensityError;
    private final String precipProbability;
    private final String precipType;
    private final String temperature;
    private final String apparentTemperature;
    private final String dewPoint;
    private final String humidity;
    private final String windSpeed;
    private final String windBearing;
    private final String visibility;
    private final String cloudCover;
    private final String pressure;
    private final String ozone;

    public CurrentForecast(String time, String summary, String icon,
                           String nearestStormDistance, String precipIntensity,
                           String precipIntensityError, String precipProbability,
                           String precipType, String temperature, String apparentTemperature,
                           String dewPoint, String humidity, String windSpeed, String windBearing,
                           String visibility, String cloudCover, String pressure, String ozone) {
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.nearestStormDistance = nearestStormDistance;
        this.precipIntensity = precipIntensity;
        this.precipIntensityError = precipIntensityError;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windBearing = windBearing;
        this.visibility = visibility;
        this.cloudCover = cloudCover;
        this.pressure = pressure;
        this.ozone = ozone;
    }

    public String getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public String getNearestStormDistance() {
        return nearestStormDistance;
    }

    public String getPrecipIntensity() {
        return precipIntensity;
    }

    public String getPrecipIntensityError() {
        return precipIntensityError;
    }

    public String getPrecipProbability() {
        return precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getApparentTemperature() {
        return apparentTemperature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindBearing() {
        return windBearing;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getPressure() {
        return pressure;
    }

    public String getOzone() {
        return ozone;
    }
}
