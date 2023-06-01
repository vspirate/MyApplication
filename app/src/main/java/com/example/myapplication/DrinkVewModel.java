package com.example.myapplication;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Pair;

import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.model.db.DrinkEntity;
import com.example.myapplication.repo.Repository;

import java.util.List;

public class DrinkVewModel  extends AndroidViewModel {
    private Repository repository;
    private List<Pair<Bitmap, DrinkEntity>> drinkData ;
    private String glass, ingredient;
    public DrinkVewModel (Application application) {
        super (application);
        repository = new Repository(application);
    }

    List<Pair<Bitmap, DrinkEntity>>  getDrinkData() {
        this.glass = glass;
        this.ingredient = ingredient;

        drinkData = repository.getAllDrinks();
        return drinkData ;
    }

    public void getDrinks(String glass,String ingredient) {
        this.ingredient = ingredient;
        this.glass = glass;
        repository.getDrinks(glass, ingredient);
    }
}
