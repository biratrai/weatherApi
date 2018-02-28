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
import com.gooner10.weatherforecast.EventBus.OnItemClickEvent;
import com.gooner10.weatherforecast.Extras.Constants;
import com.gooner10.weatherforecast.Model.DailyTemp;
import com.gooner10.weatherforecast.Model.ForeCastApiModel;
import com.gooner10.weatherforecast.R;
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

    private WeatherContract.userActions weatherPreseter = new WeatherPresenter(new ApiWeatherService(), this);

    private String LOG_TAG = TodayWeatherFragment.class.getSimpleName();

    private List<DailyTemp> dailyTempArrayListArrayList = new ArrayList<>();
    private TodayWeatherAdapter mTodayWeatherAdapter;
    ForeCastApiModel foreCastApiModel;
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_weather, container, false);

        mSearchRecyclerView = binding.recyclerViewMovie;
        mWeatherIcon = binding.weatherIconImageView;
        mLocation = binding.textViewLocation;
        mSummary = binding.textViewSummary;
        mTemp = binding.textViewTemp;

        binding.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForecastDetail.class);

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

                EventBus.getDefault().postSticky(new OnItemClickEvent(foreCastApiModel));
            }
        });

        weatherPreseter.loadData();

        // Setting the RecyclerView Layout
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Setting the Adapter
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), dailyTempArrayListArrayList);
        mSearchRecyclerView.setAdapter(mTodayWeatherAdapter);
        return binding.getRoot();
    }


    private void loadImage(String mWeatherIconString) {
        int mWeatherURL;
        Log.d(LOG_TAG, "mWeatherIconString " + mWeatherIconString);
        if (mWeatherIconString.equals(Constants.CLEAR_DAY)) {
            mWeatherURL = R.drawable.clear_day;
            Log.d(LOG_TAG, "clear-day " + mWeatherIcon);
        } else if (mWeatherIconString.equals(Constants.CLEAR_NIGHT)) {
            mWeatherURL = R.drawable.clear_night;
            Log.d(LOG_TAG, "clear-night " + mWeatherIcon);
        } else if (mWeatherIconString.equals(Constants.RAIN)) {
            mWeatherURL = R.drawable.rain;
            Log.d(LOG_TAG, "rain " + mWeatherIcon);
        } else if (mWeatherIconString.equals(Constants.SNOW)) {
            mWeatherURL = R.drawable.snow;
            Log.d(LOG_TAG, "snow " + mWeatherIcon);
        } else if (mWeatherIconString.equals(Constants.SLEET)) {
            mWeatherURL = R.drawable.sleet;
            Log.d(LOG_TAG, "snow " + mWeatherIcon);
        } else if (mWeatherIconString.equals(Constants.WIND)) {
            mWeatherURL = R.drawable.wind;
            Log.d(LOG_TAG, "snow " + mWeatherIcon);
        } else if (mWeatherIconString.equals(Constants.CLOUDY)) {
            mWeatherURL = R.drawable.cloudy;
            Log.d(LOG_TAG, "snow " + mWeatherIcon);
        } else if (mWeatherIconString.equals(Constants.PARTLY_CLOUDY_NIGHT)) {
            mWeatherURL = R.drawable.partly_cloudy_night;
            Log.d(LOG_TAG, "snow " + mWeatherIcon);
        } else {
            mWeatherURL = R.drawable.undefined;
            Log.d(LOG_TAG, "snow " + mWeatherIcon);
        }

        Glide.with(this)
                .load(mWeatherURL)
                .crossFade(30)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.ic_menu_manage)
                .into(mWeatherIcon);
    }


    @Override
    public void displayTodaysTemperature(ForeCastApiModel data) {
        this.foreCastApiModel = data;
        // Fetch the Icon String and load Image
        String mWeatherIconString = data.getDaily().getIcon();
        loadImage(mWeatherIconString);
        // Set the Location TextView
        mLocation.setText(data.getTimezone());

        mSummary.setText(data.getCurrently().getSummary());

        mTemp.setText(data.getCurrently().getTemperature());
    }

    /**
     * Bind the recycler view with the data
     */
    @Override
    public void displayWeeklyWeatherData(List<DailyTemp> weeklyWeatherDatas) {
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), weeklyWeatherDatas);
        mSearchRecyclerView.setAdapter(mTodayWeatherAdapter);
    }
}
