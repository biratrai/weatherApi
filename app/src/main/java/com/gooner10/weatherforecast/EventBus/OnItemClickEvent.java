package com.gooner10.weatherforecast.EventBus;

import com.gooner10.weatherforecast.Model.pojo.ForeCastApiModel;

public class OnItemClickEvent {
    private final ForeCastApiModel foreCastApiModel;

    public OnItemClickEvent(ForeCastApiModel foreCastApiModel) {
        this.foreCastApiModel = foreCastApiModel;
    }


    public ForeCastApiModel getData() {
        return foreCastApiModel;
    }
}
