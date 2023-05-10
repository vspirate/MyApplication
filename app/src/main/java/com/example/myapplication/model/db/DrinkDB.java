package com.example.myapplication.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.service.dao.DrinkDao;

@Database(entities = {DrinkEntity.class},version=1)
public abstract class DrinkDB extends RoomDatabase {
    public static DrinkDB instance;
    public abstract DrinkDao drinkDao();
}
