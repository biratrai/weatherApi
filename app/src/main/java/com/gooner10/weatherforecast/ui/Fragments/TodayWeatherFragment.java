package com.gooner10.weatherforecast.ui.Fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gooner10.weatherforecast.Services.ApiWeatherService;
import com.gooner10.weatherforecast.EventBus.OnItemClickEvent;
import com.gooner10.weatherforecast.Extras.Constants;
import com.gooner10.weatherforecast.Model.pojo.DailyTemp;
import com.gooner10.weatherforecast.Model.pojo.ForeCastApiModel;
import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.Model.WeatherContract;
import com.gooner10.weatherforecast.Presenters.WeatherPresenter;
import com.gooner10.weatherforecast.databinding.FragmentTodayWeatherBinding;
import com.gooner10.weatherforecast.ui.Activity.ForecastDetail;
import com.gooner10.weatherforecast.ui.Adapter.TodayWeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayWeatherFragment extends Fragment implements WeatherContract.view {
    private final String LOG_TAG = TodayWeatherFragment.class.getSimpleName();
    private final List<DailyTemp> dailyTempArrayListArrayList = new ArrayList<>();
    private TodayWeatherAdapter mTodayWeatherAdapter;
    private ForeCastApiModel foreCastApiModel;
    private final WeatherContract.userActions weatherPresenter = new WeatherPresenter(new ApiWeatherService(), this);

    private RecyclerView mSearchRecyclerView;
    private ImageView mWeatherIcon;
    private TextView mLocation;
    private TextView mSummary;
    private TextView mTemp;

    public TodayWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment with DataBinding
        FragmentTodayWeatherBinding fragmentTodayWeatherBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_today_weather, container, false);

        // Bind the Views
        mSearchRecyclerView = fragmentTodayWeatherBinding.recyclerViewDailyWeather;
        mWeatherIcon = fragmentTodayWeatherBinding.weatherIconImageView;
        mLocation = fragmentTodayWeatherBinding.textViewLocation;
        mSummary = fragmentTodayWeatherBinding.textViewSummary;
        mTemp = fragmentTodayWeatherBinding.textViewTemp;

        // Set the Layout OnClickListener
        fragmentTodayWeatherBinding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForecastDetail.class);

                // Setting the Shared View Transitions
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(),
                                mWeatherIcon, // Starting view
                                "profileWeather"); // The Shared Transition

                // Check build version for the Transition to work
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getActivity().startActivity(intent, options.toBundle());
                } else {
                    getActivity().startActivity(intent);
                }

                // Get and Post the event
                EventBus.getDefault().postSticky(new OnItemClickEvent(foreCastApiModel));
            }
        });

        weatherPresenter.loadData();

        // Setting the RecyclerView Layout
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Setting the Adapter
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), dailyTempArrayListArrayList);
        mSearchRecyclerView.setAdapter(mTodayWeatherAdapter);
        return fragmentTodayWeatherBinding.getRoot();
    }

    private void loadImage(String mWeatherIconString) {
        int mWeatherURL;
        Log.d(LOG_TAG, "mWeatherIconString " + mWeatherIconString);
        switch (mWeatherIconString) {
            case Constants.CLEAR_DAY:
                mWeatherURL = R.drawable.clear_day;
                Log.d(LOG_TAG, "clear-day " + mWeatherIcon);
                break;
            case Constants.CLEAR_NIGHT:
                mWeatherURL = R.drawable.clear_night;
                Log.d(LOG_TAG, "clear-night " + mWeatherIcon);
                break;
            case Constants.RAIN:
                mWeatherURL = R.drawable.rain;
                Log.d(LOG_TAG, "rain " + mWeatherIcon);
                break;
            case Constants.SNOW:
                mWeatherURL = R.drawable.snow;
                Log.d(LOG_TAG, "snow " + mWeatherIcon);
                break;
            case Constants.SLEET:
                mWeatherURL = R.drawable.sleet;
                Log.d(LOG_TAG, "snow " + mWeatherIcon);
                break;
            case Constants.WIND:
                mWeatherURL = R.drawable.wind;
                Log.d(LOG_TAG, "snow " + mWeatherIcon);
                break;
            case Constants.CLOUDY:
                mWeatherURL = R.drawable.cloudy;
                Log.d(LOG_TAG, "snow " + mWeatherIcon);
                break;
            case Constants.PARTLY_CLOUDY_NIGHT:
                mWeatherURL = R.drawable.partly_cloudy_night;
                Log.d(LOG_TAG, "snow " + mWeatherIcon);
                break;
            default:
                mWeatherURL = R.drawable.undefined;
                Log.d(LOG_TAG, "snow " + mWeatherIcon);
                break;
        }

        Glide.with(this)
                .load(mWeatherURL)
                .crossFade(30)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.ic_menu_manage)
                .into(mWeatherIcon);
    }

    @Override
    public void displayTodayWeather(ForeCastApiModel apiModel) {
        this.foreCastApiModel = apiModel;

        // Fetch the Icon String and load Image
        String mWeatherIconString = apiModel.getDaily().getIcon();
        loadImage(mWeatherIconString);

        // Set the TextView
        mLocation.setText(apiModel.getTimezone());

        mSummary.setText(apiModel.getCurrently().getSummary());

        mTemp.setText(apiModel.getCurrently().getTemperature());
    }

    @Override
    public void displayWeeklyWeather(List<DailyTemp> dailyTemps) {
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), dailyTemps);
        mSearchRecyclerView.setAdapter(mTodayWeatherAdapter);
    }
}
