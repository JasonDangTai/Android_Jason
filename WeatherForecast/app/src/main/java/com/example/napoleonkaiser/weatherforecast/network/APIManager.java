package com.example.napoleonkaiser.weatherforecast.network;

import com.example.napoleonkaiser.weatherforecast.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by napoleonkaiser on 30/06/2017.
 */

public interface APIManager {

    public static String BASE_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/";

    @GET("353981?apikey=T2feBhMzGsQYQq2q3wXjq37zsQDWfVy4&language=vi&details=true&metric=true")
    Call<Weather> apiGetWeathers();

}
