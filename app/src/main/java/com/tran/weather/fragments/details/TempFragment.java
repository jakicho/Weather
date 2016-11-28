package com.tran.weather.fragments.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tran.weather.R;
import com.tran.weather.models.ForecastDay;
import com.tran.weather.models.Temp;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class TempFragment extends Fragment {

    @Bind(R.id.text_temp_day) public TextView txtViewDay;
    @Bind(R.id.text_temp_night) public TextView txtViewNight;
    @Bind(R.id.text_temp_morn) public TextView txtViewMorn;
    @Bind(R.id.text_temp_eve) public TextView txtViewEve;
    @Bind(R.id.text_temp_min) public TextView txtViewMin;
    @Bind(R.id.text_temp_max) public TextView txtViewMax;

    public TempFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Temp temp = EventBus.getDefault().getStickyEvent(ForecastDay.class).getTemp();

        View view = inflater.inflate(R.layout.fragment_details_temp, container, false);

        ButterKnife.bind(this, view);

        txtViewDay.setText(String.format(getString(R.string.temperature), Math.round(temp.getDay())));
        txtViewNight.setText(String.format(getString(R.string.temperature), Math.round(temp.getNight())));
        txtViewMorn.setText(String.format(getString(R.string.temperature), Math.round(temp.getMorn())));
        txtViewEve.setText(String.format(getString(R.string.temperature), Math.round(temp.getEve())));
        txtViewMin.setText(String.format(getString(R.string.temperature), Math.round(temp.getMin())));
        txtViewMax.setText(String.format(getString(R.string.temperature), Math.round(temp.getMax())));

        return view;
    }
}
