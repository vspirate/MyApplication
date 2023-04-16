package com.example.myapplication.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class DrinksList implements Serializable {
    List<DrinkShort> drinks;
}
