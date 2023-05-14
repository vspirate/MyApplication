package com.example.myapplication.datasource;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;

import com.example.myapplication.MessageAPI;
import com.example.myapplication.model.Drink;
import com.example.myapplication.model.DrinksList;
import com.example.myapplication.model.db.DrinkDB;
import com.example.myapplication.model.db.DrinkEntity;
import com.example.myapplication.utils.ImageDownloader;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    private final MessageAPI drinkService;
    public RemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        drinkService = retrofit.create(MessageAPI.class);
    }
    public DrinksList getDrinks(String glass, String ingredient){
        Call<DrinksList> call = drinkService.getDrinkByGlasAndIngredient(glass, ingredient);
        try {
            Response<DrinksList> response = call.execute();
            if(response.isSuccessful()){
                Log.d("RemoteData",String.valueOf(response.body().toString()));
                return response.body();
            }
        }catch (IOException ioException){
            Log.e("Remote", "IOEX" + ioException.toString());
        }
        return null;
    }

    public Drink.Data getDrinkById(String id){
        Call<Drink> call = drinkService.getById(id);
        try {
            Response<Drink> response = call.execute();
            if(response.isSuccessful()){
                Log.d("RemoteData",String.valueOf(response.body().toString()));
                Drink.Data drink1 = ((Drink)response.body()).getDrinks().get(0);
                return drink1;
            }
        }catch (IOException ioException){
            Log.e("Remote", "IOEX" + ioException.toString());
        }
        return null;
    }
}
