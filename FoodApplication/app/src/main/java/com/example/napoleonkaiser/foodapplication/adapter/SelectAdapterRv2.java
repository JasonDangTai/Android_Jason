package com.example.napoleonkaiser.foodapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.napoleonkaiser.foodapplication.R;
import com.example.napoleonkaiser.foodapplication.module.Data;

import java.util.List;


public class SelectAdapterRv2 extends RecyclerView.Adapter<SelectAdapterRv2.Viewhoder> {

    private List<Data> mdata;
    private Context context;

    public SelectAdapterRv2(Context context, List<Data> data) {
        this.mdata = data;
        this.context = context;
    }

    static class Viewhoder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public Viewhoder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.im1);

        }
    }


    @Override
    public Viewhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_2, parent, false);
        return new Viewhoder(v);
    }

    @Override
    public void onBindViewHolder(Viewhoder holder, int position) {
        Glide.with(context).load(mdata.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mdata == null) return 0;
        return mdata.size();
    }


}
