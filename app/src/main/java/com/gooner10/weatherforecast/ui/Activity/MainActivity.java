package com.gooner10.weatherforecast.ui.Activity;

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
import com.gooner10.weatherforecast.ui.Adapter.TabViewAdapter;
import com.gooner10.weatherforecast.ui.Fragments.TodayWeatherFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String LOG_TAG = MainActivity.class.getSimpleName();
    private Fragment mTodayWeatherFragment = new TodayWeatherFragment();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        mToolbar = binding.toolbar;

        // Bind all of the view
        ButterKnife.bind(this);

        // Set Toolbar
        setSupportActionBar(mToolbar);

        // Set Snack bar Listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Set Toggle ActionBar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        // Setup Navigation Drawer
        navigationView.setNavigationItemSelectedListener(this);

        // Setup ViewPager
        setupViewPager();

        // Initialize Tabs
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager() {
        if (mViewPager != null) {
            TabViewAdapter mTabViewAdapter = new TabViewAdapter(getSupportFragmentManager());
            mTabViewAdapter.addFragment(mTodayWeatherFragment, "Weather");
            mViewPager.setAdapter(mTabViewAdapter);
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
            // Handle the camera action
            callToSnackBar("Activity");

        } else if (id == R.id.nav_daily_forecast) {
            callToSnackBar("Matches");
        } else if (id == R.id.nav_hourly_forecast) {
            callToSnackBar("Quick Match");
        } else if (id == R.id.nav_map) {
            callToSnackBar("Messages");
        } else if (id == R.id.nav_about) {
            callToSnackBar("Visitors");
        } else if (id == R.id.nav_contact) {
            callToSnackBar("Likes");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callToSnackBar(String message) {
        Toast.makeText(this, message + " clicked!", Toast.LENGTH_SHORT).show();
    }
}
