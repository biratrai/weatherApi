package com.gooner10.weatherforecast.Model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable {
    // parcel keys
    private static final String KEY_PHOTO_LARGE = "large";
    private static final String KEY_AGE = "age";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_MATCH = "match";
    String photo_medium;
    String photo_large;
    String user_name;
    String match;
    int age;
    LocationModel locationModel;

    public DataModel(String photo_medium, String photo_large, String user_name, String match, int age, LocationModel location) {
        this.photo_medium = photo_medium;
        this.photo_large = photo_large;
        this.user_name = user_name;
        this.match = match;
        this.age = age;
        this.locationModel = location;
    }

    protected DataModel(Parcel in) {
        photo_medium = in.readString();
        photo_large = in.readString();
        user_name = in.readString();
        match = in.readString();
        age = in.readInt();
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public String getPhoto_medium() {
        return photo_medium;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getMatch() {
        return match;
    }

    public int getAge() {
        return age;
    }

    public LocationModel getLocation() {
        return locationModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // create a bundle for the key value pairs
        Bundle bundle = new Bundle();

        // insert the key value pairs to the bundle
        bundle.putString(KEY_USER_NAME, user_name);
        bundle.putInt(KEY_AGE, age);
        bundle.putString(KEY_MATCH, match);
        bundle.putString(KEY_PHOTO_LARGE, photo_large);

        // write the key value pairs to the parcel
        dest.writeBundle(bundle);
    }

    public String getPhoto_large() {
        return photo_large;
    }
}

