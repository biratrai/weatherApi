package com.gooner10.weatherforecast.weatherdetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.databinding.ActivityForecastDetailBinding;
import com.gooner10.weatherforecast.eventbus.OnItemClickEvent;
import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;

import de.greenrobot.event.EventBus;

public class ForecastDetailActivity extends AppCompatActivity {
    private ForeCastApiModel dataModel;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityForecastDetailBinding detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_forecast_detail);
        TextView weatherTempValue = detailBinding.contentForecastDetail.weatherTempValue;
        TextView windSpeed = detailBinding.contentForecastDetail.windSpeed;
        Toolbar toolbar = detailBinding.contentForecastDetail.contentToolbar;
        CollapsingToolbarLayout collapsingToolbar = detailBinding.contentForecastDetail.collapsingToolbar;
        imageView = detailBinding.contentForecastDetail.backdrop;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Register to the bus
        EventBus.getDefault().registerSticky(this);

        // LoadBackdrop to the detail card View
        loadBackdrop();

        // Setting the Collapsing ToolBar
        collapsingToolbar.setTitle("Today's Weather");

        // Setting the values to the detail view
        weatherTempValue.setText(dataModel.getCurrently().getTemperature());
        windSpeed.setText(dataModel.getCurrently().getWindSpeed());
    }

    // onEvent Receive the Event
    public void onEventMainThread(OnItemClickEvent event) {
        dataModel = event.getData();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void loadBackdrop() {
        int weatherIconId = getIntent().getIntExtra("imageid", R.drawable.undefined);
        Glide.with(ForecastDetailActivity.this)
                .load(weatherIconId)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.undefined)
                .error(R.drawable.undefined)
                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
