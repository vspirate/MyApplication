package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class MainActivity extends AppCompatActivity {
    private DrinkVewModel viewModel;

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


        viewModel = new ViewModelProvider( this ).get(DrinkVewModel. class );


        EditText forecastDaysEdit = findViewById(R.id.forecastDaysEdit);


        squareButton.setOnClickListener(v -> {
            viewModel.getDrinks(spinner.getSelectedItem().toString(), forecastDaysEdit.getText().toString());
            switchActivities();
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, DataViewActivity.class);
        startActivity(switchActivityIntent);
    }
}