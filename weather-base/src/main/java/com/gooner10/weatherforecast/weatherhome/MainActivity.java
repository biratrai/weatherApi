package com.gooner10.weatherforecast.weatherhome;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.databinding.ActivityMainBinding;
import com.gooner10.weatherforecast.weatherhome.adapter.TabViewAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String TAG = MainActivity.class.getSimpleName();
    private final TodayWeatherFragment todayWeatherFragment = new TodayWeatherFragment();

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
                showInputDialog();
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

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        final LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.user_location_input, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String latitude = ((TextView) view.findViewById(R.id.latitude)).getText().toString();
                        String longitude = ((TextView) view.findViewById(R.id.longitude)).getText().toString();
                        todayWeatherFragment.loadData(latitude+","+longitude);
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setupViewPager() {
        if (viewPager != null) {
            TabViewAdapter tabViewAdapter = new TabViewAdapter(getSupportFragmentManager());
            tabViewAdapter.addFragment(todayWeatherFragment, "Weather");
            viewPager.setAdapter(tabViewAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
