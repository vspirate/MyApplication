package com.example.myapplication;


import com.example.myapplication.model.Drink;
import com.example.myapplication.model.DrinksList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MessageAPI {
    @POST("/api/json/v1/1/filter.php")
    Call<DrinksList> getDrinkByGlasAndIngredient(@Query( "g" ) String glass, @Query ( "i" ) String ingredient);

    @POST("/api/json/v1/1/lookup.php")
    Call<Drink> getById(@Query("i") String id);

}
