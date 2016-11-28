package com.tran.weather.network;

import com.tran.weather.models.ForecastParis;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

@Module
public class WeatherModule {

    @Provides @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(WeatherApi.URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides @Singleton
    WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides @Singleton
    Observable<ForecastParis> provideForecasts(WeatherApi weatherApi) {

        return weatherApi.get5daysObservable(WeatherApi.CITY, WeatherApi.UNITS, WeatherApi.CNT, WeatherApi.APP_ID);

    }

}
