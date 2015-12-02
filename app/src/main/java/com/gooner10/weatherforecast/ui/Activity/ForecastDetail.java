package com.gooner10.weatherforecast.ui.Activity;

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

import com.gooner10.weatherforecast.EventBus.OnItemClickEvent;
import com.gooner10.weatherforecast.Model.ForeCastApiModel;
import com.gooner10.weatherforecast.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class ForecastDetail extends AppCompatActivity {
    private ForeCastApiModel dataModel;

    @Bind(R.id.weather_info)
    TextView mWeatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Register to the bus
        EventBus.getDefault().registerSticky(this);

        // LoadBackdrop to the detail card View
        loadBackdrop();

        // Setting the Collapsing ToolBar
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Today's Weather");

        mWeatherInfo.setText(dataModel.getTimezone());

        // Floating Button with SnackBar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.detail_fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // onEvent Receive the Event
    public void onEventMainThread(OnItemClickEvent event) {
        dataModel = (ForeCastApiModel) event.getData();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
//        Glide.with(ForecastDetail.this).load(dataModel.getPhoto_large())
//                .crossFade()
//                .centerCrop()
//                .placeholder(R.drawable.image_loading)
//                .error(R.drawable.test_profile)
//                .into(imageView);
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
