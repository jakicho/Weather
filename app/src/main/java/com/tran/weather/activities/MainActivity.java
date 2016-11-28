package com.tran.weather.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tran.weather.fragments.main.MainFragment;
import com.tran.weather.R;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setTitle(getResources().getString(R.string.title_activity));

        getSupportActionBar().setSubtitle(getString(R.string.author));

        setContentView(R.layout.activity_main);

        setMainFragment(savedInstanceState);

    }


    private void setMainFragment(Bundle savedInstanceState) {

        if (savedInstanceState == null) {

            MainFragment mFrag = new MainFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.add(R.id.activity_main, mFrag, "main_frag").commit();
        }
    }
}
