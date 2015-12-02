package com.gooner10.weatherforecast.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gooner10.weatherforecast.Model.DailyTemp;
import com.gooner10.weatherforecast.R;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TodayWeatherAdapter extends RecyclerView.Adapter<TodayWeatherAdapter.ViewHolderData> {
    public LayoutInflater layoutInflater;
    public Context mContext;
    public List<DailyTemp> mDailyTemp;

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
        long mSystemMillisecond = Long.parseLong((mDailyTemp.get(position).getTime()));
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        String date = String.valueOf(new Date(mSystemMillisecond));
        Log.d("TAG", "DATE " + stamp);
        String currentTime = String.valueOf(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mSystemMillisecond);
        Log.d("TAG", "DATE " + calendar.get(Calendar.YEAR));
        Log.d("TAG", "DATE " + calendar.get(Calendar.DAY_OF_MONTH));
        Log.d("TAG", "DATE " + calendar.get(Calendar.MONTH));
        holder.mDateTextView.setText(date);
        holder.mMinTempTextView.setText("Min Temperature: " + mDailyTemp.get(position).getApparentTemperatureMin());
        holder.mMaxTempTextView.setText("Max Temperature: " + mDailyTemp.get(position).getApparentTemperatureMax());


//        Glide.with(mContext)
//                .load(mWeatherData.get(position).getPhoto_medium())
//                .override(120, 120)
//                .centerCrop()
//                .crossFade(30)
//                .placeholder(R.drawable.image_loading)
//                .error(R.drawable.test_profile)
//                .into(holder.mImageView);
//
//        holder.mUserName.setText(mWeatherData.get(position).getUser_name());
//
//        String age = String.valueOf(mWeatherData.get(position).getAge());
//        String city = mWeatherData.get(position).getLocation().getmCityName();
//        String stateCode = mWeatherData.get(position).getLocation().getmStateCode();
//        holder.mLocation.setText(age + "-" + city + "," + stateCode);
//
//        String match = Integer.parseInt(mWeatherData.get(position).getMatch()) / 100 + "%";
//        holder.mMatch.setText(match);
//
//        holder.mMatchText.setText(date);
//
//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context context = view.getContext();
//                Intent intent = new Intent(context, ForecastDetail.class);

//                DataModel mDataModel = mWeatherData.get(position);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("cupid_detail", mDataModel);
//
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation((Activity) context,
//                                holder.mImageView, // Starting view
//                                "profileImage"); // The Shared Transition
//                context.startActivity(intent);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                    context.startActivity(intent, options.toBundle());
//                } else {
//                    context.startActivity(intent);
//                }
//                // Get and Post the event
//                EventBus.getDefault().postSticky(new OnItemClickEvent(bundle));
//            }
//        });
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
