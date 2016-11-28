package com.tran.weather.network;

import com.tran.weather.models.ForecastParis;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public interface WeatherApi {

    String URL = "http://api.openweathermap.org/";
    String APP_ID = "8194ea842a9aef80a798c8ac0c320ec4";
    String CITY = "Paris";
    String UNITS = "metric";
    int CNT = 5;

    @GET("data/2.5/forecast/daily?")
    Observable<ForecastParis> get5daysObservable(
            @Query("q") String city,
            @Query("units") String units,
            @Query("cnt") int cnt,
            @Query("appid") String appid);

}
