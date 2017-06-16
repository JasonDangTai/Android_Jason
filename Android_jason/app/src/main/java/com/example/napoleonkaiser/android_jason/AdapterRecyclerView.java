package com.example.napoleonkaiser.android_jason;

import android.media.Image;
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

class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {
    private List<String> mList = new ArrayList<>();

    AdapterRecyclerView(List<String> list) {
        if (list != null) mList.addAll(list);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView_1, textView_2;
        ImageView img;

        ViewHolder(View v) {
            super(v);
            textView_1 = (TextView) v.findViewById(R.id.text_view_1);
            textView_2 = (TextView) v.findViewById(R.id.text_view_2);
            img = (ImageView) v.findViewById(R.id.image);
            Glide.with(v).load("https://upload.wikimedia.org/wikipedia/commons/2/2e/Fast_food_meal.jpg").into(img);
        }
    }

    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerView.ViewHolder holder, int position) {
        holder.textView_1.setText(mList.get(position));
        holder.textView_2.setText(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}