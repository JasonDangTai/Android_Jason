package com.example.napoleonkaiser.mvpexample;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.napoleonkaiser.mvpexample.Adapter.MyListAdapter;
import com.example.napoleonkaiser.mvpexample.Data.Book;
import com.example.napoleonkaiser.mvpexample.Data.Username;
import com.example.napoleonkaiser.mvpexample.utils.BasicImageDownloader;
import com.example.napoleonkaiser.mvpexample.utils.RecyclerItemClickListener;
import com.example.napoleonkaiser.mvpexample.utils.SaveLoadImage;

import java.io.File;
import java.util.concurrent.ExecutionException;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainPresenterImpl mainPresenter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainPresenter = new MainPresenterImpl(this, new MainInteractorImpl());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void setItem(final Realm realm) {
        final RealmResults<Book> books = realm.where(Book.class).findAllAsync();
        books.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Book>>() {
            @Override
            public void onChange(RealmResults<Book> books, OrderedCollectionChangeSet changeSet) {
                if(changeSet==null){
                    recyclerView.setAdapter(new MyListAdapter(getApplication(), books));
            }
            else {

                }
            }
        });



        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        SaveLoadImage save = new SaveLoadImage();
                        save.getBitmapFromURL(getBaseContext(), books.get(position).getImageUrl(), position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mainPresenter.onItemClicked(position);

    }
}
