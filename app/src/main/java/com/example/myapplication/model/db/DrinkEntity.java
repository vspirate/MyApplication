package com.example.myapplication.model.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myapplication.model.Drink;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@lombok.Data
@Entity
@AllArgsConstructor
public class DrinkEntity {
        @PrimaryKey
        @NonNull
        public String idDrink = UUID.randomUUID().toString();
        public String strDrink;
        public String strDrinkAlternate;
        public String strTags;
        public String strVideo;
        public String strCategory;
        public String strIBA;
        public String strAlcoholic;
        public String strGlass;
        public String strInstructions;
        public String strInstructionsES;
        public String strInstructionsDE;
        public String strInstructionsFR;
        public String strInstructionsIT;
        public String strDrinkThumb;
        public String strIngredient1;
        public String strIngredient2;
        public String strIngredient3;
        public String strIngredient4;
        public String strIngredient5;
        public String strIngredient6;
        public String strIngredient7;
        public String strIngredient8;
        public String strIngredient9;
        public String strIngredient10;
        public String strIngredient11;
        public String strIngredient12;
        public String strIngredient13;
        public String strIngredient14;
        public String strIngredient15;
        public String strMeasure1;
        public String strMeasure2;
        public String strMeasure3;
        public String strMeasure4;
        public String strMeasure5;
        public String strMeasure6;
        public String strMeasure7;
        public String strMeasure8;
        public String strMeasure9;
        public String strMeasure10;
        public String strMeasure11;
        public String strMeasure12;
        public String strMeasure13;
        public String strMeasure14;
        public String strMeasure15;
        public String strImageSource;
        public String strImageAttribution;
        public String strCreativeCommonsConfirmed;
        public String dateModified;

        public DrinkEntity(){
        }

        public DrinkEntity(Drink.Data data){
                idDrink = data.getIdDrink();
                strDrink = data.getStrDrink();
                strDrinkAlternate = data.getStrDrinkAlternate();
                strTags = data.getStrTags();
                strVideo = data.getStrVideo();
                strCategory = data.getStrCategory();
                strIBA = data.getStrIBA();
                strAlcoholic = data.getStrAlcoholic();
                strGlass = data.getStrGlass();
                strInstructions = data.getStrInstructions();
                strInstructionsES = data.getStrInstructionsES();
                strInstructionsDE = data.getStrInstructionsDE();
                strInstructionsFR = data.getStrInstructionsFR();
                strInstructionsIT = data.getStrInstructionsIT();
                strDrinkThumb = data.getStrDrinkThumb();
                strIngredient1 = data.getStrIngredient1();
                strIngredient2 = data.getStrIngredient2();
                strIngredient3 = data.getStrIngredient3();
                strIngredient4 = data.getStrIngredient4();
                strIngredient5 = data.getStrIngredient5();
                strIngredient6 = data.getStrIngredient6();
                strIngredient7 = data.getStrIngredient7();
                strIngredient8 = data.getStrIngredient8();
                strIngredient9 = data.getStrIngredient9();
                strIngredient10 = data.getStrIngredient10();
                strIngredient11 = data.getStrIngredient11();
                strIngredient12 = data.getStrIngredient12();
                strIngredient13 = data.getStrIngredient13();
                strIngredient14 = data.getStrIngredient14();
                strIngredient15 = data.getStrIngredient15();
                strMeasure1 = data.getStrMeasure1();
                strMeasure2 = data.getStrMeasure2();
                strMeasure3 = data.getStrMeasure3();
                strMeasure4 = data.getStrMeasure4();
                strMeasure5 = data.getStrMeasure5();
                strMeasure6 = data.getStrMeasure6();
                strMeasure7 = data.getStrMeasure7();
                strMeasure8 = data.getStrMeasure8();
                strMeasure9 = data.getStrMeasure9();
                strMeasure10 = data.getStrMeasure10();
                strMeasure11 = data.getStrMeasure11();
                strMeasure12 = data.getStrMeasure12();
                strMeasure13 = data.getStrMeasure13();
                strMeasure14 = data.getStrMeasure14();
                strMeasure15 = data.getStrMeasure15();
                strImageSource = data.getStrImageSource();
                strImageAttribution = data.getStrImageAttribution();
                strCreativeCommonsConfirmed = data.getStrCreativeCommonsConfirmed();
                dateModified = data.getDateModified();
        }
}
