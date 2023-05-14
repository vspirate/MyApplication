package com.example.myapplication.service.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.model.db.DrinkEntity;

import java.util.List;

@Dao
public interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //ВАЖЛИВО для нашої задачі
    public void insertDrink(DrinkEntity drinkEntity);

    @Query("Select * FROM DrinkEntity where dateModified ='now'")
    public DrinkEntity getOneDrink();

    @Query("Select * FROM DrinkEntity")
    List<DrinkEntity> getAllDrinks();
}

