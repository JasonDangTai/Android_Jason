package com.example.napoleonkaiser.android_jason2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.napoleonkaiser.android_jason2.adapter.MyAdapter;
import com.example.napoleonkaiser.android_jason2.module.Data;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {


    private List<Data> myData = new ArrayList<Data>() {};
    private RecyclerView mRecyclerViewl;
    private String string = "What is Lorem Ipsum?\n" +
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
            "\n" +
            "Why do we use it?\n" +
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
            "\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerViewl = (RecyclerView) findViewById(R.id.mRecyclerView);
        fakeData();
        mRecyclerViewl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewl.setNestedScrollingEnabled(false);
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),myData);
        mRecyclerViewl.setAdapter(myAdapter);
    }

    private void fakeData() {
//        Data data = new Data("akgdfkjasdhfg", "https://s-media-cache-ak0.pinimg.com/736x/ca/ea/57/caea57268e1dee696f3c20a5a0f895f2.jpg");
//        myData.add(0, data);
        for (int i = 0; i < 10; i++) {

         Data   data = new Data(string, "https://www.w3schools.com/css/img_fjords.jpg");
            myData.add(data);
        }
    }
}
