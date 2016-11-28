package com.tran.weather.models;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp implements Parcelable {

    @SerializedName("day")
    @Expose
    private Double day;
    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("max")
    @Expose
    private Double max;
    @SerializedName("night")
    @Expose
    private Double night;
    @SerializedName("eve")
    @Expose
    private Double eve;
    @SerializedName("morn")
    @Expose
    private Double morn;


    /**
     * @return The day
     */
    public Double getDay() {
        return day;
    }

    /**
     * @param day The day
     */
    public void setDay(Double day) {
        this.day = day;
    }

    /**
     * @return The min
     */
    public Double getMin() {
        return min;
    }

    /**
     * @param min The min
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     * @return The max
     */
    public Double getMax() {
        return max;
    }

    /**
     * @param max The max
     */
    public void setMax(Double max) {
        this.max = max;
    }

    /**
     * @return The night
     */
    public Double getNight() {
        return night;
    }

    /**
     * @param night The night
     */
    public void setNight(Double night) {
        this.night = night;
    }

    /**
     * @return The eve
     */
    public Double getEve() {
        return eve;
    }

    /**
     * @param eve The eve
     */
    public void setEve(Double eve) {
        this.eve = eve;
    }

    /**
     * @return The morn
     */
    public Double getMorn() {
        return morn;
    }

    /**
     * @param morn The morn
     */
    public void setMorn(Double morn) {
        this.morn = morn;
    }

    // Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeDouble(min);
        parcel.writeDouble(max);
        parcel.writeDouble(night);
        parcel.writeDouble(day);
        parcel.writeDouble(eve);
        parcel.writeDouble(morn);

    }

    public static final Parcelable.Creator<Temp> CREATOR = new Parcelable.Creator<Temp>() {

        public Temp createFromParcel(Parcel in) {
            return new Temp(in);
        }

        public Temp[] newArray(int size) {
            return new Temp[size];
        }

    };

    private Temp(Parcel in) {

        min = in.readDouble();
        max = in.readDouble();
        night = in.readDouble();
        day = in.readDouble();
        eve = in.readDouble();
        morn = in.readDouble();
    }
}