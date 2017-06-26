package com.example.napoleonkaiser.mvpexample.Adapter;

/**
 * Created by napoleonkaiser on 23/06/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.napoleonkaiser.mvpexample.Data.Book;
import com.example.napoleonkaiser.mvpexample.R;
import com.example.napoleonkaiser.mvpexample.utils.SaveLoadImage;

import java.io.File;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> implements RealmChangeListener {

    private final RealmResults<Book> mBook;
    private Context context;


    public MyListAdapter(Context context, RealmResults<Book> mBook) {
        this.context = context;
        this.mBook = mBook;
        mBook.addChangeListener(this);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvAuthor, tvDe;
        private ImageView im1;

        public ViewHolder(final View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.text_books_title);
            tvAuthor = (TextView) view.findViewById(R.id.text_books_author);
            tvDe = (TextView) view.findViewById(R.id.text_books_description);
            im1 = (ImageView) view.findViewById(R.id.image_background);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_books, parent, false);
        return new ViewHolder(view);

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mBook.get(position).getTitle());
        holder.tvAuthor.setText(mBook.get(position).getAuthor());
        holder.tvDe.setText(mBook.get(position).getDescription());
        if (isNetworkAvailable()) {
            Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
            Glide.with(context).load(mBook.get(position).getImageUrl()).into(holder.im1);
        } else {
            Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
            SaveLoadImage load = new SaveLoadImage();
            File myImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + "image_test" + File.separator);
            String s = "jason" + position + ".jpeg";
            Bitmap bitmap = load.loadImageFromStorage( myImageFile, s);
            holder.im1.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return mBook.size();
    }

    @Override
    public void onChange(Object o) {
        notifyDataSetChanged();
    }

}
