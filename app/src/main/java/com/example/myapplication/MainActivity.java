package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.DrinksList;
import com.example.myapplication.model.db.DrinkEntity;
import com.example.myapplication.repo.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private Repository repository;

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

        repository=new Repository(this);

        EditText forecastDaysEdit = findViewById(R.id.forecastDaysEdit);


        squareButton.setOnClickListener(v -> {
            repository.getDrinks(spinner.getSelectedItem().toString(), forecastDaysEdit.getText().toString());
            switchActivities();
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, DataViewActivity.class);
        startActivity(switchActivityIntent);
    }
}