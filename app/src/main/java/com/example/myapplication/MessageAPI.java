package com.example.myapplication;


import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MessageAPI {
    @POST("/v1/weather/Forecast")
    Call<Weather> getWeatherByCityName(@Query( "location" ) String city, @Query ( "days" ) String forecast_days);

}
