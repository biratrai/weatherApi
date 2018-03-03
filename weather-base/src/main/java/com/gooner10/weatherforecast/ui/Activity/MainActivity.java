package com.gooner10.weatherforecast.ui.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private final Fragment mTodayWeatherFragment = new TodayWeatherFragment();

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind all of the view
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = mainBinding.appbar.toolbar;
        viewPager = mainBinding.appbar.contentMain.viewpager;
        DrawerLayout drawerLayout = mainBinding.drawerLayout;
        FloatingActionButton fab = mainBinding.appbar.fab;
        NavigationView navigationView = mainBinding.navView;
        TabLayout tabLayout = mainBinding.appbar.tabs;

        // Set Toolbar
        setSupportActionBar(toolbar);

        // Set Snack bar Listener

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is a Snackbar in action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Set Toggle ActionBar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        // Setup Navigation Drawer
        navigationView.setNavigationItemSelectedListener(this);

        // Setup ViewPager
        setupViewPager();

        // Initialize Tabs
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager() {
        if (viewPager != null) {
            TabViewAdapter mTabViewAdapter = new TabViewAdapter(getSupportFragmentManager());
            mTabViewAdapter.addFragment(mTodayWeatherFragment, "Weather");
            viewPager.setAdapter(mTabViewAdapter);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_overview) {
            callToNavMenu("Overview");
        } else if (id == R.id.nav_daily_forecast) {
            callToNavMenu("Daily Forecast");
        } else if (id == R.id.nav_hourly_forecast) {
            callToNavMenu("Hourly Forecast");
        } else if (id == R.id.nav_map) {
            callToNavMenu("Maps");
        } else if (id == R.id.nav_about) {
            callToNavMenu("About");
        } else if (id == R.id.nav_contact) {
            callToNavMenu("Contact");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callToNavMenu(String message) {
        Toast.makeText(this, message + " clicked!", Toast.LENGTH_SHORT).show();
    }
}
