package com.example.myapplication;


import com.example.myapplication.model.Drink;
import com.example.myapplication.model.DrinkShort;
import com.example.myapplication.model.DrinksList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MessageAPI {
    @POST("/api/json/v1/1/filter.php")
    Call<DrinksList> getWeatherByCityName(@Query( "g" ) String glass, @Query ( "i" ) String ingredient);

    @POST("/api/json/v1/1/lookup.php")
    Call<Drink> getById(@Query("i") String id);

}
