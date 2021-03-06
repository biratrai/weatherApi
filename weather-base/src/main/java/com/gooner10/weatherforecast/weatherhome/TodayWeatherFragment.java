package com.gooner10.weatherforecast.weatherhome;

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
import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.databinding.FragmentTodayWeatherBinding;
import com.gooner10.weatherforecast.eventbus.OnItemClickEvent;
import com.gooner10.weatherforecast.model.pojo.DailyTemp;
import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;
import com.gooner10.weatherforecast.weatherUtils.Constants;
import com.gooner10.weatherforecast.weatherdetail.ForecastDetailActivity;
import com.gooner10.weatherforecast.weatherhome.adapter.TodayWeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayWeatherFragment extends Fragment implements WeatherContract.view {

    private String TAG = TodayWeatherFragment.class.getSimpleName();
    private WeatherContract.userActions weatherPresenter = new WeatherPresenter(this);
    private List<DailyTemp> dailyTempArrayListArrayList = new ArrayList<>();
    private TodayWeatherAdapter mTodayWeatherAdapter;
    private ForeCastApiModel foreCastApiModel;
    private RecyclerView recyclerView;
    private ImageView weatherIcon;
    private TextView location;
    private TextView summary;
    private TextView temp;
    int weatherIconId;

    public TodayWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentTodayWeatherBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_weather, container, false);

        recyclerView = binding.recyclerViewDailyWeather;
        weatherIcon = binding.weatherIconImageView;
        location = binding.textViewLocation;
        summary = binding.textViewSummary;
        temp = binding.textViewTemp;

        binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForecastDetailActivity.class);
                intent.putExtra("imageid", weatherIconId);

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

        weatherPresenter.loadData("38.968,-76.873");

        // Setting the RecyclerView Layout
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Setting the Adapter
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), dailyTempArrayListArrayList);
        recyclerView.setAdapter(mTodayWeatherAdapter);
        return binding.getRoot();
    }


    private void loadImage(String weatherIconString) {

        Log.d(TAG, "weatherIconString " + weatherIconString);
        switch (weatherIconString) {
            case Constants.CLEAR_DAY:
                weatherIconId = R.drawable.clear_day;
                Log.d(TAG, "clear-day " + this.weatherIcon);
                break;
            case Constants.CLEAR_NIGHT:
                weatherIconId = R.drawable.clear_night;
                Log.d(TAG, "clear-night " + this.weatherIcon);
                break;
            case Constants.RAIN:
                weatherIconId = R.drawable.rain;
                Log.d(TAG, "rain " + this.weatherIcon);
                break;
            case Constants.SNOW:
                weatherIconId = R.drawable.snow;
                Log.d(TAG, "snow " + this.weatherIcon);
                break;
            case Constants.SLEET:
                weatherIconId = R.drawable.sleet;
                Log.d(TAG, "snow " + this.weatherIcon);
                break;
            case Constants.WIND:
                weatherIconId = R.drawable.wind;
                Log.d(TAG, "snow " + this.weatherIcon);
                break;
            case Constants.CLOUDY:
                weatherIconId = R.drawable.cloudy;
                Log.d(TAG, "snow " + this.weatherIcon);
                break;
            case Constants.PARTLY_CLOUDY_NIGHT:
                weatherIconId = R.drawable.partly_cloudy_night;
                Log.d(TAG, "snow " + this.weatherIcon);
                break;
            default:
                weatherIconId = R.drawable.undefined;
                Log.d(TAG, "snow " + this.weatherIcon);
                break;
        }

        Glide.with(this)
                .load(weatherIconId)
                .crossFade(30)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.ic_menu_manage)
                .into(this.weatherIcon);
    }


    @Override
    public void displayTodaysTemperature(ForeCastApiModel data) {
        Log.d(TAG, "displayTodayWeather: ");
        this.foreCastApiModel = data;
        // Fetch the Icon String and load Image
        String mWeatherIconString = data.getDaily().getIcon();
        loadImage(mWeatherIconString);
        // Set the Location TextView
        location.setText(data.getTimezone());

        summary.setText(data.getCurrently().getSummary());
        String weatherText = data.getCurrently().getTemperature() + " °F";
        temp.setText(weatherText);
    }

    /**
     * Bind the recycler view with the data
     */
    @Override
    public void displayWeeklyWeatherData(List<DailyTemp> weeklyWeatherDatas) {
        Log.d(TAG, "displayWeeklyWeather: ");
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), weeklyWeatherDatas);
        recyclerView.setAdapter(mTodayWeatherAdapter);
    }

    @Override
    public void loadData(String locationCoordinate) {
        weatherPresenter.loadData(locationCoordinate);
    }
}
