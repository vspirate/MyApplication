package com.example.myapplication.datasource;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.myapplication.model.db.DrinkDB;
import com.example.myapplication.model.db.DrinkEntity;

import java.util.List;

public class LocalDataSource {
    final DrinkDB db;

    public LocalDataSource(Context context){
        db = Room.databaseBuilder(context, DrinkDB.class,"drinks").
        fallbackToDestructiveMigration().build();
    }
    public void storeDrinks(DrinkEntity de){
        db.drinkDao().insertDrink(de);
    }
    public DrinkEntity getDrink(){
        //Log.d("Запрос из базы",String.valueOf(db.drinkDao().getOneDrink().getIdDrink());
        return db.drinkDao().getOneDrink();
    }
    public List<DrinkEntity> getDrinks(){
        return db.drinkDao().getAllDrinks();
    }
}
