package com.gooner10.weatherforecast.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gooner10.weatherforecast.Model.pojo.DailyTemp;
import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.WeatherUtils.RecyclerViewAnimUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TodayWeatherAdapter extends RecyclerView.Adapter<TodayWeatherAdapter.ViewHolderData> {
    public LayoutInflater layoutInflater;
    public Context mContext;
    public List<DailyTemp> mDailyTemp;
    private int mPreviousPosition = 0;

    public TodayWeatherAdapter(Context mContext, List<DailyTemp> mDailyTemp) {
        layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mDailyTemp = mDailyTemp;
    }

    @Override
    public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.daily_weather_row, parent, false);
        ViewHolderData viewHolderData = new ViewHolderData(view);

        return viewHolderData;
    }

    @Override
    public void onBindViewHolder(final ViewHolderData holder, final int position) {
        long mSystemMillisecond = Long.parseLong((mDailyTemp.get(position).getTime()))*1000;
        Date date = new Date(mSystemMillisecond);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String mDailyDate = fmt.format(date);

        holder.mDateTextView.setText(mDailyDate);
        holder.mMinTempTextView.setText("Min Temperature: " + mDailyTemp.get(position).getApparentTemperatureMin());
        holder.mMaxTempTextView.setText("Max Temperature: " + mDailyTemp.get(position).getApparentTemperatureMax());

        boolean flag = (position > mPreviousPosition);
        RecyclerViewAnimUtils.animateSunblind(holder, flag);

        mPreviousPosition = position;
    }

    @Override
    public int getItemCount() {
        return mDailyTemp.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        public final View mView;

        @Bind(R.id.MinTempTextView)
        TextView mMinTempTextView;

        @Bind(R.id.DateTextView)
        TextView mDateTextView;

        @Bind(R.id.MaxTempTextView)
        TextView mMaxTempTextView;

        public ViewHolderData(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
