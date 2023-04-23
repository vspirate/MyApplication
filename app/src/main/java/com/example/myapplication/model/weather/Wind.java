package com.example.myapplication.model.weather;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Wind implements Serializable {

    private Double speed;
    private Integer deg;
    private Double gust;

}
