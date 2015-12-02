package com.gooner10.weatherforecast.ui.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gooner10.weatherforecast.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyWeatherFragment extends Fragment {
    private String LOG_TAG = DailyWeatherFragment.class.getSimpleName();

    public DailyWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nearby, container, false);

    }

}
