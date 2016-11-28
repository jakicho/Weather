package com.tran.weather.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tran.weather.fragments.details.MiscFragment;
import com.tran.weather.fragments.details.TempFragment;
import com.tran.weather.fragments.details.WeatherFragment;
import com.tran.weather.R;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setLayout();

        setToolbar();

        setFragments(savedInstanceState);

    }

    private void setLayout() {

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            setContentView(R.layout.activity_details_land);

        } else setContentView(R.layout.activity_details);
    }

    private void setToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getResources().getString(R.string.title_activity_details) + " " + EventBus.getDefault().getStickyEvent(String.class));

    }

    private void setFragments(Bundle savedInstanceState) {

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_container_weather, new WeatherFragment())
                    .add(R.id.frame_container_temp, new TempFragment())
                    .add(R.id.frame_container_misc, new MiscFragment())
                    .commit();
        }
    }
}
