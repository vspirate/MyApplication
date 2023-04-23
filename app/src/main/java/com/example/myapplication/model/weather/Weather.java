package com.example.myapplication.model.weather;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Weather implements Serializable {
    private Integer id;
    private String main;
    private String description;
    private String icon;


}
