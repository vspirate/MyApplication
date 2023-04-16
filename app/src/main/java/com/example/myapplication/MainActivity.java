package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.Drink;
import com.example.myapplication.model.DrinkShort;
import com.example.myapplication.model.DrinksList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.first_input, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.glassEdit);
        spinner.setAdapter(adapter);

        Button squareButton = findViewById(R.id.getButton);//описали змінну та зв’язали її з кнопкою

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessageAPI messageAPI = retrofit.create(MessageAPI.class);


        squareButton.setOnClickListener(v -> {
            EditText forecastDaysEdit = findViewById(R.id.forecastDaysEdit);
            messageAPI.getWeatherByCityName(spinner.getSelectedItem().toString(), forecastDaysEdit.getText().toString()).enqueue(new Callback<DrinksList>() {
                @Override
                public void onResponse(@NonNull Call<DrinksList> call, @NonNull Response<DrinksList> response) {
                    switchActivities(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<DrinksList> call, @NonNull Throwable t) {
                    Log.i("Volodymyr", "Failure " + t);
                }
            });
        });
    }

    private void switchActivities(DrinksList drinksList) {
        Intent switchActivityIntent = new Intent(this, DataViewActivity.class);
        switchActivityIntent.putExtra("drinks", drinksList);
        startActivity(switchActivityIntent);
    }
}