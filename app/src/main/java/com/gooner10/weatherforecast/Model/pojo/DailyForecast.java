package com.gooner10.weatherforecast.Model.pojo;

import java.util.List;

public class DailyForecast {
    String summary;
    String icon;
    List<DailyTemp> data;


    public DailyForecast(String summary, String icon, List<DailyTemp> data) {
        this.summary = summary;
        this.icon = icon;
        this.data = data;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<DailyTemp> getData() {
        return data;
    }

    public void setData(List<DailyTemp> data) {
        this.data = data;
    }
}
