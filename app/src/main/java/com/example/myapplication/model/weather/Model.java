package com.example.myapplication.model.weather;

import java.io.Serializable;

import lombok.Data;

@Data
public class Model implements Serializable {

    private String cod;
    private Integer message;
    private Integer cnt;
    private java.util.List<List> list;
    private City city;

}
