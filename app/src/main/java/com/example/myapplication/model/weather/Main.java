package com.example.myapplication.model.weather;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Main implements Serializable {

    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Double pressure;
    private Double seaLevel;
    private Double grnd_level;
    private Double humidity;
    private Double temp_kf;


}
