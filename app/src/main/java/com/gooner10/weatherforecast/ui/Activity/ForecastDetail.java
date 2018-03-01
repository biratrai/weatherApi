package com.gooner10.weatherforecast.ui.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gooner10.weatherforecast.eventBus.OnItemClickEvent;
import com.gooner10.weatherforecast.model.pojo.ForeCastApiModel;
import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.databinding.ActivityForecastDetailBinding;
import de.greenrobot.event.EventBus;

public class ForecastDetail extends AppCompatActivity {
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
        FloatingActionButton fab = detailBinding.contentForecastDetail.detailFabButton;
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

        // Floating Button with SnackBar
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is a Snack bar in action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        Glide.with(ForecastDetail.this).load(R.id.backdrop)
                .crossFade()
                .centerCrop()
                .placeholder(R.drawable.background)
                .error(R.drawable.background)
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
