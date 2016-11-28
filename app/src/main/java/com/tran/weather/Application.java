package com.tran.weather;

import com.tran.weather.network.DaggerWeatherComponent;
import com.tran.weather.network.WeatherComponent;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class Application extends android.app.Application {

    protected static Application application;
    protected WeatherComponent weatherComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        weatherComponent = DaggerWeatherComponent.builder().build();

    }

    public static Application app() {
        return application;
    }

    public WeatherComponent weatherComponent() {
        return weatherComponent;
    }


}
