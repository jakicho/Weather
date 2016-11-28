package com.tran.weather.models;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastParis {

    @SerializedName("list")
    @Expose
    private ArrayList<ForecastDay> list = new ArrayList<ForecastDay>();


    /**
     * @return The 5 days list (Details)
     */
    public List<ForecastDay> getList() {
        return list;
    }

    /**
     *
     * @param list
     * The list
     */
    public void setList(ArrayList<ForecastDay> list) {
        this.list = list;
    }
}