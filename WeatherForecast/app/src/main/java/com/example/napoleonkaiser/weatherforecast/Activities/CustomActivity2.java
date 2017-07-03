package com.example.napoleonkaiser.weatherforecast.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.napoleonkaiser.weatherforecast.R;
import com.example.napoleonkaiser.weatherforecast.Utils.CustomView;
import com.hendraanggrian.widget.RevealFrameLayout;



public class CustomActivity2 extends AppCompatActivity {

    public final static String EXTRA_RECT = "com.example.circularreveal.CustomActivity2";

    RevealFrameLayout layout;
    TextView textView;
    CustomView customView;
    private Rect rect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom2);

        layout = findViewById(R.id.circularrevealframelayout);
        customView = findViewById(R.id.custom);

        rect = getIntent().getParcelableExtra(EXTRA_RECT);
        customView.setBackgroundColor(123);
        customView.post(new Runnable() {
            @Override
            public void run() {
                layout.animate(customView, rect.centerX(), rect.centerY()).start();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Animator animator = layout.animate(customView, rect.centerX(), rect.centerY(), true);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                customView.setVisibility(View.INVISIBLE);
                finish();
                overridePendingTransition(0, 0);
            }
        });
        animator.start();
    }
}