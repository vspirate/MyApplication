package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.m3o.com/v1/weather/Forecast")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MessageAPI messageAPI=retrofit.create(MessageAPI.class);
        Button squareButton=findViewById(R.id.getButton);//описали змінну та зв’язали її з кнопкою
        squareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText city = findViewById(R.id.cityEdit);
                EditText forecastDaysEdit = findViewById(R.id.forecastDaysEdit);
                messageAPI.getWeatherByCityName(city.getText().toString(), forecastDaysEdit.getText().toString()).enqueue(new Callback<Weather>() {
                    @Override
                    public void onResponse(Call<Weather> call, Response<Weather> response) {
                        Log. i ( "Volodymyr" , response.body().toString());
                        Log. i ( "Volodymyr" , "OK" );
                    }

                    @Override
                    public void onFailure(Call<Weather> call, Throwable t) {
                        Log. i ( "Volodymyr" , "Failure " +t);
                    }
                });
            }
        });
    }


}