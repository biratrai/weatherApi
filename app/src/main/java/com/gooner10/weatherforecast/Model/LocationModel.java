package com.gooner10.weatherforecast.Model;

public class LocationModel {
    String mCountryCode;
    String mCountryName;
    String mCityName;
    String mStateName;
    String mStateCode;


    public LocationModel(String mCountryCode, String mCountryName, String mCityName, String mStateName, String mStateCode) {
        this.mCountryCode = mCountryCode;
        this.mCountryName = mCountryName;
        this.mCityName = mCityName;
        this.mStateName = mStateName;
        this.mStateCode = mStateCode;
    }

    public String getmCountryCode() {
        return mCountryCode;
    }

    public void setmCountryCode(String mCountryCode) {
        this.mCountryCode = mCountryCode;
    }

    public String getmCountryName() {
        return mCountryName;
    }

    public void setmCountryName(String mCountryName) {
        this.mCountryName = mCountryName;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmStateName() {
        return mStateName;
    }

    public void setmStateName(String mStateName) {
        this.mStateName = mStateName;
    }

    public String getmStateCode() {
        return mStateCode;
    }

    public void setmStateCode(String mStateCode) {
        this.mStateCode = mStateCode;
    }
}

