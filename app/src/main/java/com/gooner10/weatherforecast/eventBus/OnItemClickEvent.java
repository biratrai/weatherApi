package com.gooner10.weatherforecast.eventBus;

import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;

public class OnItemClickEvent {
    private final ForeCastApiModel foreCastApiModel;

    public OnItemClickEvent(ForeCastApiModel foreCastApiModel) {
        this.foreCastApiModel = foreCastApiModel;
    }


    public ForeCastApiModel getData() {
        return foreCastApiModel;
    }
}
