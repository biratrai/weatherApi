package com.gooner10.weatherforecast.ui.Fragments;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gooner10.weatherforecast.EventBus.OnItemClickEvent;
import com.gooner10.weatherforecast.Extras.Constants;
import com.gooner10.weatherforecast.Model.DailyTemp;
import com.gooner10.weatherforecast.Model.ForeCastApiModel;
import com.gooner10.weatherforecast.Network.VolleySingleton;
import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.ui.Activity.ForecastDetail;
import com.gooner10.weatherforecast.ui.Adapter.TodayWeatherAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayWeatherFragment extends Fragment {
    private String LOG_TAG = TodayWeatherFragment.class.getSimpleName();
    private List<DailyTemp> dailyTempArrayListArrayList = new ArrayList<>();
    private TodayWeatherAdapter mTodayWeatherAdapter;
    private ForeCastApiModel foreCastApiModel;

    @Bind(R.id.recyclerViewMovie)
    RecyclerView mSearchRecyclerView;

    @Bind(R.id.weather_icon_imageView)
    ImageView mWeatherIcon;

    @Bind(R.id.textView_location)
    TextView mLocation;

    @Bind(R.id.textView_summary)
    TextView mSummary;

    @Bind(R.id.textView_temp)
    TextView mTemp;

    public TodayWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        JsonParser();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_weather, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
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

                // Get and Post the event
                EventBus.getDefault().postSticky(new OnItemClickEvent(foreCastApiModel));
            }
        });

        // Inject the Views
        ButterKnife.bind(this, view);

        // Setting the RecyclerView Layout
        mSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Setting the Adapter
        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), dailyTempArrayListArrayList);
        mSearchRecyclerView.setAdapter(mTodayWeatherAdapter);
        return view;
    }

    private void setTodayWeather() {

        // Fetch the Icon String and load Image
        String mWeatherIconString = foreCastApiModel.getDaily().getIcon();
        loadImage(mWeatherIconString);

        // Set the Location TextView
        mLocation.setText(foreCastApiModel.getTimezone());

        mSummary.setText(foreCastApiModel.getCurrently().getSummary());

        mTemp.setText(foreCastApiModel.getCurrently().getTemperature());
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

    public void JsonParser() {
        final String url = "https://api.forecast.io/forecast/203bf0976335ed98863b556ed9f61f79/38.968,-76.873";
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(LOG_TAG, "response" + response);
                parseJSONresponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG, "Error Message" + error.getMessage());
            }
        });
        Log.d(LOG_TAG, "Error Message" + jsObjRequest);
        requestQueue.add(jsObjRequest);
    }

    private void parseJSONresponse(JSONObject response) {
        Gson gson = new Gson();
        foreCastApiModel = gson.fromJson(response.toString(), ForeCastApiModel.class);
        Log.d(LOG_TAG, "latitude: " + foreCastApiModel.getLatitude());
        Log.d(LOG_TAG, "getTimezone: " + foreCastApiModel.getTimezone());
        Log.d(LOG_TAG, "getApparentTemperature: " + foreCastApiModel.getCurrently().getApparentTemperature());
        Log.d(LOG_TAG, "getData: " + foreCastApiModel.getDaily().getData().getClass());
        for (DailyTemp dailyTemp : foreCastApiModel.getDaily().getData()) {
            Log.d(LOG_TAG, "getTemperatureMax: " + dailyTemp.getTemperatureMax());
        }
        dailyTempArrayListArrayList = foreCastApiModel.getDaily().getData();
        mSearchRecyclerView.getAdapter().notifyDataSetChanged();

        mTodayWeatherAdapter = new TodayWeatherAdapter(getActivity(), dailyTempArrayListArrayList);
        mSearchRecyclerView.setAdapter(mTodayWeatherAdapter);
        setTodayWeather();
    }

}
