package com.example.napoleonkaiser.weatherforecast.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.napoleonkaiser.weatherforecast.R;
import com.example.napoleonkaiser.weatherforecast.Utils.RecyclerItemClickListener;
import com.example.napoleonkaiser.weatherforecast.adapter.MyAdapter;
import com.example.napoleonkaiser.weatherforecast.model.Weather;
import com.example.napoleonkaiser.weatherforecast.network.APIManager;
import com.hendraanggrian.widget.RevealFrameLayout;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RevealFrameLayout layout;
    View buttonSimple;
    View buttonFrom;
    View buttonFull;
    View maskSimple;
    View maskTo;
    TextView tvDate, tvTemperature, tvTemmax, tvTemMin, tvShortPhrase, tvRainProbability, tvUVIndex;
    RecyclerView rv;
    Weather list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        layout = findViewById(R.id.circularrevealframelayout);
        buttonSimple = findViewById(R.id.buttonSimple);
        buttonFrom = findViewById(R.id.buttonFrom);
        buttonFull = findViewById(R.id.buttonFull);
        maskSimple = findViewById(R.id.maskSimple);
        maskTo = findViewById(R.id.maskTo);
        rv = findViewById(R.id.rv);
        tvDate = findViewById(R.id.tvDate);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvTemmax = findViewById(R.id.tvTemMax);
        tvTemMin = findViewById(R.id.tvTemMin);
        tvShortPhrase = findViewById(R.id.tvShortPhrase);
        tvRainProbability = findViewById(R.id.tvRainProbability);
        tvUVIndex = findViewById(R.id.tvUVIndex);

        buttonSimple.setOnClickListener(this);
        buttonFrom.setOnClickListener(this);
        buttonFull.setOnClickListener(this);
        maskSimple.setOnClickListener(this);
        maskTo.setOnClickListener(this);
        tvDate.setText(date);


        getData();

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv.setNestedScrollingEnabled(false);

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), rv, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, CustomActivity2.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.putExtra(CustomActivity2.EXTRA_RECT, createRect((ViewGroup) buttonFull.getParent(), buttonFull));
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }

    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIManager service = retrofit.create(APIManager.class);


        service.apiGetWeathers().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                // Get data from server
                list = response.body();
                MyAdapter myAdapter = new MyAdapter(getApplication(), list.getDailyForecasts());
                rv.setAdapter(myAdapter);
                Double tem = (list.getDailyForecasts().get(0).getTemperature().getMaximum().getValue() / 2) + (list.getDailyForecasts().get(0).getTemperature().getMinimum().getValue() / 2);
                tvTemperature.setText(tem + "°");
                tvTemmax.setText(list.getDailyForecasts().get(0).getTemperature().getMaximum().getValue() + "°");
                tvTemMin.setText(list.getDailyForecasts().get(0).getTemperature().getMinimum().getValue() + "°");
                tvShortPhrase.setText(list.getDailyForecasts().get(0).getDay().getIconPhrase());
                tvRainProbability.setText(list.getDailyForecasts().get(0).getDay().getRainProbability()+"");

                // Show data in view

                Log.i("Test", list.getDailyForecasts().get(0).getDate());


            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.i("error", t.getMessage());
                Toast.makeText(MainActivity.this, "On Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSimple:
                Animator animator1 = layout.animate(maskSimple);
                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        maskSimple.setVisibility(View.VISIBLE);
                    }
                });
                animator1.start();
                break;
            case R.id.maskSimple:
                Animator animator2 = layout.animate(maskSimple, true);
                animator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        maskSimple.setVisibility(View.INVISIBLE);
                    }
                });
                animator2.start();
                break;
            case R.id.buttonFrom:
                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.setInterpolator(new FastOutSlowInInterpolator());
                animatorSet1.playTogether(layout.animateTo(buttonFrom, maskTo));
                animatorSet1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        buttonFrom.setVisibility(View.INVISIBLE);
                        maskTo.setVisibility(View.VISIBLE);
                    }
                });
                animatorSet1.start();
                break;
            case R.id.maskTo:
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.setInterpolator(new FastOutSlowInInterpolator());
                animatorSet2.playTogether(layout.animateTo(buttonFrom, maskTo, true));
                animatorSet2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        buttonFrom.setVisibility(View.VISIBLE);
                        maskTo.setVisibility(View.INVISIBLE);
                    }
                });
                animatorSet2.start();
                break;
            case R.id.buttonFull:
                Intent intent = new Intent(this, CustomActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra(CustomActivity2.EXTRA_RECT, createRect((ViewGroup) buttonFull.getParent(), buttonFull));
                startActivity(intent);
                break;
        }

    }

    @NonNull
    private static Rect createRect(@NonNull ViewGroup parent, @NonNull View view) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        parent.offsetDescendantRectToMyCoords(view, rect);
        return rect;
    }
}
