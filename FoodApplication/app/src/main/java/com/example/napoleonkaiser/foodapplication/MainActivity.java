package com.example.napoleonkaiser.foodapplication;

import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.napoleonkaiser.foodapplication.adapter.MyAdapter;
import com.example.napoleonkaiser.foodapplication.module.Data;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage, toolbarTitle;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private FragmentTransaction fragmentTransaction;
    private StoreFragment storeFragment;
    private SelectFragment selectFragment;
    private MagazineFragment magazineFragment;
    private CartFragment cartFragment;
    private MeFragment meFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_select:
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, selectFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                    mTextMessage.setText("");
                    toolbarTitle.setText("Foodi");
                    return true;
                case R.id.navigation_store:
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, storeFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                    mTextMessage.setText("Store");
                    toolbarTitle.setText("Foodi Mag");
                    toolbar.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_magazine:
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, magazineFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                    mTextMessage.setText("Magazine");
                    toolbarTitle.setText("");
                    return true;
                case R.id.navigation_cart:
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, cartFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                    mTextMessage.setText("Cart");
                    toolbarTitle.setText("Foodi Mag");
                    return true;
                case R.id.navigation_me:
                    fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, meFragment);
                    fragmentTransaction.commitAllowingStateLoss();
                    mTextMessage.setText("Me");
                    toolbarTitle.setText("Foodi Mag");
                    toolbar.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        frameLayout = (FrameLayout) findViewById(R.id.content);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        storeFragment = new StoreFragment();
        selectFragment = new SelectFragment();
        magazineFragment = new MagazineFragment();
        cartFragment = new CartFragment();
        meFragment = new MeFragment();

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, storeFragment);
        fragmentTransaction.commitAllowingStateLoss();
        navigation.setSelectedItemId(R.id.navigation_store);

        setSupportActionBar(toolbar);
        toolbar.setTitle("");

/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (shouldChangeStatusBarTintToDark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }*/
    }

}
