package com.tran.weather.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tran.weather.R;
import com.tran.weather.models.ForecastDay;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class ForecastsAdapter extends RecyclerView.Adapter<ForecastsAdapter.ViewHolder>{

    private LayoutInflater inflater;

    private Calendar c;

    private final String[] days;

    private List<ForecastDay> mDataset = Collections.emptyList();

    private final PublishSubject<Integer> onClickSubject = PublishSubject.create();

    private Context context;

    public ForecastsAdapter(Context context, List<ForecastDay> mDataset){

        inflater = LayoutInflater.from(context);

        this.mDataset = mDataset;

        this.context = context;

        days = context.getResources().getStringArray(R.array.days);

        c = Calendar.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view;

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            view = inflater.inflate(R.layout.item_recycler_horizontal, viewGroup, false);

        } else view = inflater.inflate(R.layout.item_recycler_vertical, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        ForecastDay current= mDataset.get(position);
        int iconId = context.getResources().getIdentifier("ic_" + current.getWeather().get(0).getIcon(), "drawable", context.getPackageName());

        holder.txtViewWeatherDescription.setText(current.getWeather().get(0).getDescription());
        holder.imgViewWeatherIcon.setImageResource(iconId);

        holder.txtViewTempMin.setText(
                String.format(context.getString((R.string.temperature)),
                Math.round(current.getTemp().getMin())));

        holder.txtViewTempMax.setText(
                String.format(context.getString((R.string.temperature)),
                Math.round(current.getTemp().getMax())));

        holder.txtViewDay.setText(days[((c.get(Calendar.DAY_OF_WEEK) + position ) % 7)]);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubject.onNext(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public Observable<Integer> getPositionClicks() {
        return onClickSubject.asObservable();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView txtViewDay;
        TextView txtViewTempMin;
        TextView txtViewTempMax;
        TextView txtViewWeatherDescription;
        ImageView imgViewWeatherIcon;

        public ViewHolder(View itemView) {

            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            txtViewDay = (TextView) itemView.findViewById(R.id.txt_day);
            txtViewTempMin = (TextView) itemView.findViewById(R.id.text_temp_min);
            txtViewTempMax = (TextView) itemView.findViewById(R.id.text_temp_max);
            txtViewWeatherDescription = (TextView) itemView.findViewById(R.id.txt_weather_description);

            imgViewWeatherIcon = (ImageView) itemView.findViewById(R.id.img_weather_icon);
        }
    }

    public void setDataset(List<ForecastDay> mDataset) {
        this.mDataset = mDataset;
    }

}
