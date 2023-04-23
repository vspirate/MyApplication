package com.example.myapplication;


import com.example.myapplication.model.weather.Model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MessageAPI {
    @POST("/data/2.5/forecast")
    Call<Model> getWeatherByCityName(@Query( "q" ) String city, @Query( "appid" ) String appid);

}
