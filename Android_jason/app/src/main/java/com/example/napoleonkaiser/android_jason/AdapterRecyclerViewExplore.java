package com.example.napoleonkaiser.android_jason;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by napoleonkaiser on 13/06/2017.
 */

class AdapterRecyclerViewExplore extends RecyclerView.Adapter<AdapterRecyclerViewExplore.ViewHolder>{
    private List<String> mList = new ArrayList<>();
    AdapterRecyclerViewExplore(List<String> list) {
        if (list != null) mList.addAll(list);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_1, textView_2, textView_3, textView_4, textView_5, textView_6;
        ImageView img1, img2;

        ViewHolder(View v) {
            super(v);
            textView_1 = (TextView) v.findViewById(R.id.tv_1);
            textView_2 = (TextView) v.findViewById(R.id.tv_2);
            textView_3 = (TextView) v.findViewById(R.id.tv_3);
            textView_4 = (TextView) v.findViewById(R.id.tv_4);
            textView_5 = (TextView) v.findViewById(R.id.tv_5);
            textView_6 = (TextView) v.findViewById(R.id.tv_6);
            img1 = (ImageView) v.findViewById(R.id.image1);
            img2 = (ImageView) v.findViewById(R.id.image2);
            Glide.with(v).load("https://upload.wikimedia.org/wikipedia/commons/2/2e/Fast_food_meal.jpg").into(img1);
            Glide.with(v).load("https://static.independent.co.uk/s3fs-public/styles/article_small/public/thumbnails/image/2016/12/19/18/sush0istock-gkrphoto.jpg").into(img2);
        }
    }

    @Override
    public AdapterRecyclerViewExplore.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_explore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerViewExplore.ViewHolder holder, int position) {
        holder.textView_1.setText(mList.get(position));
        holder.textView_3.setText(mList.get(position));
        holder.textView_4.setText(mList.get(position));
        holder.textView_5.setText(mList.get(position));
        holder.textView_6.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}