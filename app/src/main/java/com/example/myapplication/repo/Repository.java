package com.example.myapplication.repo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Pair;

import com.example.myapplication.datasource.LocalDataSource;
import com.example.myapplication.datasource.RemoteDataSource;
import com.example.myapplication.model.Drink;
import com.example.myapplication.model.DrinkShort;
import com.example.myapplication.model.DrinksList;
import com.example.myapplication.model.db.DrinkEntity;
import com.example.myapplication.utils.ImageDownloader;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
public class Repository {
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    public Repository(Context context){
        localDataSource = new LocalDataSource(context);
        remoteDataSource = new RemoteDataSource();
    }

    public void getDrinks(String glass, String ingredient){
        Executors.newSingleThreadExecutor().execute(()->{
            DrinksList drinksList = remoteDataSource.getDrinks(glass, ingredient);
            storeDrinks(drinksList);
        });
        //return localDataSource.getDrinks();
    }

    public void storeDrinks(DrinksList drinksList){
        for (DrinkShort drink : drinksList.getDrinks()) {//                Pair<Bitmap, Drink.Data> drinkWithImage = new Pair<>(ImageDownloader.downloadImagesByUrl(drink1.getStrDrinkThumb()+"/preview"), drink1);
            if(drink!=null)
                storeDrink(remoteDataSource.getDrinkById(drink.getIdDrink()));
        }
    }
    public void storeDrink(Drink.Data data){
        if(data==null) return;
        DrinkEntity drinkEntity = new DrinkEntity(data);
        drinkEntity.dateModified = "now";
        localDataSource.storeDrinks(drinkEntity);
    }

    public List<Pair<Bitmap, DrinkEntity>> getAllDrinks() {
        return localDataSource.getDrinks().stream().map(d-> new Pair<>(ImageDownloader.downloadImagesByUrl(d.getStrDrinkThumb() + "/preview"), d)).collect(Collectors.toList());
    }
}
