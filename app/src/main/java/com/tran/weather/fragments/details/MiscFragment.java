package com.tran.weather.fragments.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tran.weather.R;
import com.tran.weather.models.ForecastDay;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class MiscFragment extends Fragment{

    @Bind(R.id.text_misc_humidity) public TextView txtHumidity;
    @Bind(R.id.text_misc_pressure) public TextView txtPressure;
    @Bind(R.id.text_misc_speed) public TextView txtSpeed;

    public MiscFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_misc, container, false);

        ButterKnife.bind(this, view);

        Double pressure = EventBus.getDefault().getStickyEvent(ForecastDay.class).getPressure();
        Double speed = EventBus.getDefault().getStickyEvent(ForecastDay.class).getSpeed();
        int humidity = EventBus.getDefault().getStickyEvent(ForecastDay.class).getHumidity();

        txtHumidity.setText(String.valueOf(humidity));
        txtPressure.setText(String.valueOf(pressure));
        txtSpeed.setText(String.valueOf(speed));

        return view;
    }
}
