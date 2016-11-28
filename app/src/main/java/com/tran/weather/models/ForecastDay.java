package com.tran.weather.models;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastDay implements Parcelable {

    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather = new ArrayList<>();
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("rain")
    @Expose
    private Double rain;

    /**
     * @return The temp
     */
    public Temp getTemp() {
        return temp;
    }

    /**
     * @param temp The temp
     */
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    /**
     * @return The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * @return The weather
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * @param weather The weather
     */
    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    /**
     * @return The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return The deg
     */
    public Integer getDeg() {
        return deg;
    }

    /**
     * @param deg The deg
     */
    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    /**
     * @return The clouds
     */
    public Integer getClouds() {
        return clouds;
    }

    /**
     * @param clouds The clouds
     */
    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    /**
     * @return The rain
     */
    public Double getRain() {
        return rain;
    }

    /**
     * @param rain The rain
     */
    public void setRain(Double rain) {
        this.rain = rain;
    }

    // Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeDouble(pressure);
        parcel.writeDouble(speed);
        parcel.writeDouble(rain);

        parcel.writeInt(humidity);
        parcel.writeInt(deg);
        parcel.writeInt(clouds);

        parcel.writeParcelable(temp, i); // object
        parcel.writeTypedList(weather); // ArrayList<object>

    }

    public static final Parcelable.Creator<ForecastDay> CREATOR = new Parcelable.Creator<ForecastDay>() {

        public ForecastDay createFromParcel(Parcel in) {
            return new ForecastDay(in);
        }

        public ForecastDay[] newArray(int size) {
            return new ForecastDay[size];
        }

    };

    private ForecastDay(Parcel in) {

        pressure = in.readDouble();
        speed = in.readDouble();
        rain = in.readDouble();

        humidity = in.readInt();
        deg = in.readInt();
        clouds = in.readInt();

        temp = in.readParcelable(getClass().getClassLoader());
        in.readTypedList(weather, Weather.CREATOR);
    }

}
