package com.gooner10.weatherforecast.Model;

public class DailyTemp {
    String time;
    String summary;
    String icon;
    String sunriseTime;
    String sunsetTime;
    String moonPhase;
    String precipIntensity;
    String precipIntensityMax;
    String precipIntensityMaxTime;
    String precipProbability;
    String precipType;
    String temperatureMin;
    String temperatureMinTime;
    String temperatureMax;
    String temperatureMaxTime;
    String apparentTemperatureMin;
    String apparentTemperatureMinTime;
    String apparentTemperatureMax;
    String apparentTemperatureMaxTime;
    String dewPoint;
    String humidity;
    String windSpeed;
    String windBearing;
    String visibility;
    String cloudCover;
    String pressure;
    String ozone;

    public DailyTemp(String time, String summary, String icon, String sunriseTime,
                         String sunsetTime, String moonPhase, String precipIntensity,
                         String precipIntensityMax, String precipIntensityMaxTime,
                         String precipProbability, String precipType, String temperatureMin,
                         String temperatureMinTime, String temperatureMax, String temperatureMaxTime,
                         String apparentTemperatureMin, String apparentTemperatureMinTime,
                         String apparentTemperatureMax, String apparentTemperatureMaxTime,
                         String dewPoint, String humidity, String windSpeed, String windBearing,
                         String visibility, String cloudCover, String pressure, String ozone) {
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.moonPhase = moonPhase;
        this.precipIntensity = precipIntensity;
        this.precipIntensityMax = precipIntensityMax;
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperatureMin = temperatureMin;
        this.temperatureMinTime = temperatureMinTime;
        this.temperatureMax = temperatureMax;
        this.temperatureMaxTime = temperatureMaxTime;
        this.apparentTemperatureMin = apparentTemperatureMin;
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        this.apparentTemperatureMax = apparentTemperatureMax;
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
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

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public String getPrecipIntensity() {
        return precipIntensity;
    }

    public String getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public String getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public String getPrecipProbability() {
        return precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public String getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public String getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public String getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public String getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public String getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public String getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
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
