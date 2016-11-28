package com.tran.weather.fragments.main;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tran.weather.Application;
import com.tran.weather.R;
import com.tran.weather.activities.DetailsActivity;
import com.tran.weather.adapters.ForecastsAdapter;
import com.tran.weather.models.ForecastDay;
import com.tran.weather.models.ForecastParis;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by TRAN_Jacques on 21/11/2016.
 */

public class MainFragment extends Fragment {

    @Bind(R.id.recycler_view) public RecyclerView recyclerView;

    private ForecastsAdapter mAdapter;

    private List<ForecastDay> mDataset;

    @Inject Observable<ForecastParis> forecastsObservable;

    private Subscription mSubscriptionForecasts; // 5 days Paris forecasts

    private Subscription mSubscriptionClick; // item click

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, view);

        Application.app().weatherComponent().inject(this);

        setRecyclerView();

        loadForecasts();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mSubscriptionForecasts != null &&  !mSubscriptionForecasts.isUnsubscribed()) {

            mSubscriptionForecasts.unsubscribe();
        }

        if(mSubscriptionClick != null &&  !mSubscriptionClick.isUnsubscribed()) {

            mSubscriptionClick.unsubscribe();
        }
    }

    private void setRecyclerView() {

        if(mDataset == null) mDataset = Collections.emptyList();

        mAdapter = new ForecastsAdapter(getContext(), mDataset);

        recyclerView.setAdapter(mAdapter);

        // layouts
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            recyclerView.setLayoutManager(new LinearLayoutManager(

                    getContext(), LinearLayout.HORIZONTAL, false
            ));

        } else {

            recyclerView.setLayoutManager(new GridLayoutManager(

                    getContext(),1, GridLayoutManager.VERTICAL, false
            ));
        }

        // item click
        mSubscriptionClick = mAdapter.getPositionClicks()

                .subscribeOn(Schedulers.newThread())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer position) {

                        String[] days = getContext().getResources().getStringArray(R.array.days_full);

                        EventBus.getDefault().postSticky(mDataset.get(position)); // detail

                        EventBus.getDefault().postSticky(days[((Calendar.getInstance().get(Calendar.DAY_OF_WEEK) + position ) % 7)]); // subtitle

                        startActivity(new Intent(getContext(), DetailsActivity.class));

                    }
                });
    }

    private void loadForecasts() {

        mSubscriptionForecasts = forecastsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ForecastParis>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ForecastParis forecastParis) {

                        // update recycler
                        mDataset = forecastParis.getList();
                        mAdapter.setDataset(mDataset);
                        mAdapter.notifyDataSetChanged();

                    }
                });
    }
}
