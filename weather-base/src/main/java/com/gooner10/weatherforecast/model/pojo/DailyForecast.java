package com.gooner10.weatherforecast.model.pojo;

import java.util.List;

public class DailyForecast {
    private String summary;
    private String icon;
    private List<DailyTemp> data;


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
