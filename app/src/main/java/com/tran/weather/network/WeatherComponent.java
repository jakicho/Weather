package com.tran.weather.network;

import com.tran.weather.fragments.main.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

@Singleton
@Component(modules = {
        WeatherModule.class,
})

public interface WeatherComponent {
    void inject(MainFragment mainFragment);
}
