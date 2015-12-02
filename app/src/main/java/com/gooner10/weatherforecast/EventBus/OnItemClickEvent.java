package com.gooner10.weatherforecast.EventBus;

import com.gooner10.weatherforecast.Model.ForeCastApiModel;

public class OnItemClickEvent {
    public final ForeCastApiModel foreCastApiModel;

    public OnItemClickEvent(ForeCastApiModel foreCastApiModel) {
        this.foreCastApiModel = foreCastApiModel;
    }


    public ForeCastApiModel getData() {
        return foreCastApiModel;
    }
}
