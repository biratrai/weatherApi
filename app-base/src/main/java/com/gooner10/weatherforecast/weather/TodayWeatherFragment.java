package com.gooner10.weatherforecast.weather;

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
import com.gooner10.weatherforecast.eventBus.OnItemClickEvent;
import com.gooner10.weatherforecast.extras.Constants;
import com.gooner10.weatherforecast.model.WeatherContract;
import com.gooner10.weatherforecast.model.pojo.DailyTemp;
import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;
import com.gooner10.weatherforecast.presenters.WeatherPresenter;
import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.services.ApiWeatherService;
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

    FragmentTodayWeatherBinding binding;

    private WeatherContract.userActions weatherPresenter = new WeatherPresenter(new ApiWeatherService(), this);

    private String LOG_TAG = TodayWeatherFragment.class.getSimpleName();

    private List<DailyTemp> dailyTempArrayListArrayList = new ArrayList<>();
    private TodayWeatherAdapter mTodayWeatherAdapter;
    ForeCastApiModel foreCastApiModel;
    private RecyclerView recyclerView;
    private ImageView weatherIcon;
    private TextView location;
    private TextView summary;
    private TextView temp;

    public TodayWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_weather, container, false);

        recyclerView = binding.recyclerViewDailyWeather;
        weatherIcon = binding.weatherIconImageView;
        location = binding.textViewLocation;
        summary = binding.textViewSummary;
        temp = binding.textViewTemp;

        binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForecastDetail.class);

                // Setting the Shared View Transitions
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(),
                                weatherIcon, // Starting view
                                "profileWeather"); // The Shared Transition

                // Check build version for the Transition to work
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getActivity().startActivity(intent, options.toBundle());
                } else {
                    getActivity().startActivity(intent);
                }

                EventBus.getDefault().postSticky(new OnItemClickEvent(foreCastApiModel));
            }
        });

        weatherPresenter.loadData();

        // Setting the RecyclerView Layout
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Setting the Adapter
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), dailyTempArrayListArrayList);
        recyclerView.setAdapter(mTodayWeatherAdapter);
        return binding.getRoot();
    }


    private void loadImage(String weatherIconString) {
        int weatherIcon;
        Log.d(LOG_TAG, "weatherIconString " + weatherIconString);
        switch (weatherIconString) {
            case Constants.CLEAR_DAY:
                weatherIcon = R.drawable.clear_day;
                Log.d(LOG_TAG, "clear-day " + this.weatherIcon);
                break;
            case Constants.CLEAR_NIGHT:
                weatherIcon = R.drawable.clear_night;
                Log.d(LOG_TAG, "clear-night " + this.weatherIcon);
                break;
            case Constants.RAIN:
                weatherIcon = R.drawable.rain;
                Log.d(LOG_TAG, "rain " + this.weatherIcon);
                break;
            case Constants.SNOW:
                weatherIcon = R.drawable.snow;
                Log.d(LOG_TAG, "snow " + this.weatherIcon);
                break;
            case Constants.SLEET:
                weatherIcon = R.drawable.sleet;
                Log.d(LOG_TAG, "snow " + this.weatherIcon);
                break;
            case Constants.WIND:
                weatherIcon = R.drawable.wind;
                Log.d(LOG_TAG, "snow " + this.weatherIcon);
                break;
            case Constants.CLOUDY:
                weatherIcon = R.drawable.cloudy;
                Log.d(LOG_TAG, "snow " + this.weatherIcon);
                break;
            case Constants.PARTLY_CLOUDY_NIGHT:
                weatherIcon = R.drawable.partly_cloudy_night;
                Log.d(LOG_TAG, "snow " + this.weatherIcon);
                break;
            default:
                weatherIcon = R.drawable.undefined;
                Log.d(LOG_TAG, "snow " + this.weatherIcon);
                break;
        }

        Glide.with(this)
                .load(weatherIcon)
                .crossFade(30)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.ic_menu_manage)
                .into(this.weatherIcon);
    }


    @Override
    public void displayTodayWeather(ForeCastApiModel data) {
        this.foreCastApiModel = data;
        // Fetch the Icon String and load Image
        String mWeatherIconString = data.getDaily().getIcon();
        loadImage(mWeatherIconString);
        // Set the Location TextView
        location.setText(data.getTimezone());

        summary.setText(data.getCurrently().getSummary());

        temp.setText(data.getCurrently().getTemperature() + " °F");
    }

    /**
     * Bind the recycler view with the data
     */
    @Override
    public void displayWeeklyWeather(List<DailyTemp> weeklyWeatherDatas) {
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), weeklyWeatherDatas);
        recyclerView.setAdapter(mTodayWeatherAdapter);
    }
}