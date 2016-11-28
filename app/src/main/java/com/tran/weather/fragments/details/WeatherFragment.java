package com.tran.weather.fragments.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tran.weather.R;
import com.tran.weather.models.ForecastDay;
import com.tran.weather.models.Weather;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class WeatherFragment extends Fragment{

    @Bind(R.id.image_detail_icon) public ImageView imgIcon;
    @Bind(R.id.text_detail_description) public TextView txtDescription;

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_weather, container, false);

        ButterKnife.bind(this, view);

        Weather weather = EventBus.getDefault().getStickyEvent(ForecastDay.class).getWeather().get(0);

        txtDescription.setText(weather.getDescription());

        imgIcon.setImageResource(getContext().getResources()
                .getIdentifier("ic_" + weather.getIcon(), "drawable", getContext().getPackageName())
        );

        return view;
    }

}
