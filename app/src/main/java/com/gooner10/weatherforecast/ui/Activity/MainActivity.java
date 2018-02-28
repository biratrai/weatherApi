package com.gooner10.weatherforecast.ui.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.databinding.ActivityMainBinding;
import com.gooner10.weatherforecast.ui.Adapter.TabViewAdapter;
import com.gooner10.weatherforecast.weather.TodayWeatherFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String LOG_TAG = MainActivity.class.getSimpleName();
    private Fragment mTodayWeatherFragment = new TodayWeatherFragment();

    ActivityMainBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(layout.appbar.toolbar);

        // Set Snack bar Listener

        layout.appbar.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is a Snackbar in action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Set Toggle ActionBar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, layout.drawerLayout, layout.appbar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        layout.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        // Setup Navigation Drawer
        layout.navView.setNavigationItemSelectedListener(this);

        // Setup ViewPager
        setupViewPager();

        // Initialize Tabs
        layout.appbar.tabs.setupWithViewPager(layout.appbar.contentMain.viewpager);
    }

    private void setupViewPager() {
        if (layout.appbar.contentMain.viewpager != null) {
            TabViewAdapter mTabViewAdapter = new TabViewAdapter(getSupportFragmentManager());
            mTabViewAdapter.addFragment(mTodayWeatherFragment, "Weather");
            layout.appbar.contentMain.viewpager.setAdapter(mTabViewAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_overview) {
            callToNavMenu("Activity");
        } else if (id == R.id.nav_daily_forecast) {
            callToNavMenu("Matches");
        } else if (id == R.id.nav_hourly_forecast) {
            callToNavMenu("Quick Match");
        } else if (id == R.id.nav_map) {
            callToNavMenu("Messages");
        } else if (id == R.id.nav_about) {
            callToNavMenu("Visitors");
        } else if (id == R.id.nav_contact) {
            callToNavMenu("Likes");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callToNavMenu(String message) {
        Toast.makeText(this, message + " clicked!", Toast.LENGTH_SHORT).show();
    }
}
