package com.example.myapplication.model.weather;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class List implements Serializable {

    private Integer dt;
    private Main main;
    private java.util.List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private Integer visibility;
    private Double pop;
    private Sys sys;
    private String dtTxt;
    private Rain rain;

}
