package com.gooner10.weatherforecast.weatherdetail.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gooner10.weatherforecast.model.pojo.DailyTemp;
import com.gooner10.weatherforecast.R;
import com.gooner10.weatherforecast.weatherUtils.RecyclerViewAnimUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

public class TodayWeatherAdapter extends RecyclerView.Adapter<TodayWeatherAdapter.ViewHolderData> {
    private final LayoutInflater layoutInflater;
    private final List<DailyTemp> dailyTemps;
    private int mPreviousPosition = 0;

    public TodayWeatherAdapter(Context context, List<DailyTemp> dailyTemps) {
        layoutInflater = LayoutInflater.from(context);
        this.dailyTemps = dailyTemps;
    }

    @Override
    public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.daily_weather_row, parent, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderData holder, final int position) {
        long mSystemMillisecond = Long.parseLong((dailyTemps.get(position).getTime())) * 1000;
        Date date = new Date(mSystemMillisecond);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String mDailyDate = fmt.format(date);

        holder.dateText.setText(mDailyDate);
        holder.minTempText.setText("Min Temperature: " + dailyTemps.get(position).getApparentTemperatureMin());
        holder.maxTempText.setText("Max Temperature: " + dailyTemps.get(position).getApparentTemperatureMax());

        boolean flag = (position > mPreviousPosition);
        RecyclerViewAnimUtils.animateSunblind(holder, flag);

        mPreviousPosition = position;
    }

    @Override
    public int getItemCount() {
        return dailyTemps.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        public final View mView;

//        @BindView(R.id.MinTempTextView)
        TextView minTempText;

//        @BindView(R.id.DateTextView)
        TextView dateText;

//        @BindView(R.id.MaxTempTextView)
        TextView maxTempText;

        public ViewHolderData(View itemView) {
            super(itemView);
            mView = itemView;
            minTempText = itemView.findViewById((R.id.MinTempTextView));
            dateText = itemView.findViewById((R.id.DateTextView));
            maxTempText = itemView.findViewById(R.id.MaxTempTextView);
            ButterKnife.bind(this, itemView);
        }
    }
}
