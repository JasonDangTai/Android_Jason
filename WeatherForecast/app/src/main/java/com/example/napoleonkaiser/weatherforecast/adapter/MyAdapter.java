package com.example.napoleonkaiser.weatherforecast.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.napoleonkaiser.weatherforecast.R;
import com.example.napoleonkaiser.weatherforecast.model.DailyForecast;
import com.example.napoleonkaiser.weatherforecast.model.Weather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by napoleonkaiser on 16/06/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewhoder> {

    private List<DailyForecast> mdata;
    private Context context;
    private String date, date3;

    public MyAdapter(Context context, List<DailyForecast> data) {
        this.mdata = data;
        this.context = context;
    }

    static class Viewhoder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView tv1, tv2, tv3;

        public Viewhoder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.im1);
            tv1 = (TextView) v.findViewById(R.id.tvThu);
            tv2 = (TextView) v.findViewById(R.id.tvNgay);
            tv3 = (TextView) v.findViewById(R.id.tv2);
        }
    }


    @Override
    public Viewhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new Viewhoder(v);
    }

    @Override
    public void onBindViewHolder(Viewhoder holder, int position) {
            String target = mdata.get(position).getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            DateFormat df2 = new SimpleDateFormat("EEE");
            DateFormat df3 = new SimpleDateFormat("dd-MM");
        try {
            Date result =  df.parse(target);
            date = df2.format(result.getTime());
            date3 = df3.format(result.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
            holder.tv2.setText(date3);
            holder.tv1.setText(date);
            holder.tv3.setText(mdata.get(position).getTemperature().getMinimum().getValue()+"°/" +
                                mdata.get(position).getTemperature().getMaximum().getValue()+ "°");

    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }


}
