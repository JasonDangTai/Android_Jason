package com.example.napoleonkaiser.android_jason2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.napoleonkaiser.android_jason2.R;
import com.example.napoleonkaiser.android_jason2.module.Data;

import java.util.List;

/**
 * Created by napoleonkaiser on 16/06/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewhoder> {

    private List<Data> mdata;
    private Context context;

    public MyAdapter(Context context, List<Data> data) {
//        if (data != null) mdata.addAll(data);
        this.mdata = data;
        this.context = context;
    }

    static class Viewhoder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public Viewhoder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.im1);
            textView = (TextView) v.findViewById(R.id.tv1);

        }
    }


    @Override
    public Viewhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        return new Viewhoder(v);
    }

    @Override
    public void onBindViewHolder(Viewhoder holder, int position) {
        holder.textView.setText((CharSequence) mdata.get(position).getText());
        Glide.with(context).load(mdata.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mdata == null) return 0;
        return mdata.size();
    }


}
