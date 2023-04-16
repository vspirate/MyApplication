package com.example.myapplication.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DrinkShort implements Serializable {

    private String strDrink;
    private String strDrinkThumb;
    private String idDrink;

}