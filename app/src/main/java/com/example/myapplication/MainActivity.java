package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.weather.Model;

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


        Button squareButton = findViewById(R.id.getButton);//описали змінну та зв’язали її з кнопкою

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessageAPI messageAPI = retrofit.create(MessageAPI.class);


        squareButton.setOnClickListener(v -> {
            EditText forecastDaysEdit = findViewById(R.id.textCity);
            messageAPI.getWeatherByCityName(forecastDaysEdit.getText().toString(), "3d822b9dce4e57f12b9b3400d480a358").enqueue(new Callback<Model>() {
                @Override
                public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                    Log.i("Volodymyr", String.valueOf(response.body()));
                    switchActivities(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                    Log.i("Volodymyr", "Failure " + t);
                }
            });
        });
    }

    private void switchActivities(Model model) {
        Intent switchActivityIntent = new Intent(this, DataViewActivity.class);
        switchActivityIntent.putExtra("model", model);
        startActivity(switchActivityIntent);
    }
}