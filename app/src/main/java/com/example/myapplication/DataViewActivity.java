package com.example.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.DataRecyclerViewAdapter;
import com.example.myapplication.model.Drink;
import com.example.myapplication.model.DrinkShort;
import com.example.myapplication.model.DrinksList;
import com.example.myapplication.utils.ImageDownloader;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataViewActivity extends AppCompatActivity  implements DataRecyclerViewAdapter.ItemClickListener{

    RecyclerView recyclerView;
    DataRecyclerViewAdapter adapter;

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);
        ArrayList<Pair<Bitmap, Drink.Data>> drinks = new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataRecyclerViewAdapter(this, drinks);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MessageAPI messageAPI = retrofit.create(MessageAPI.class);

        for (DrinkShort drink : ((DrinksList)getIntent().getSerializableExtra("drinks")).getDrinks()) {
            messageAPI.getById(drink.getIdDrink()).enqueue(new Callback<Drink>() {
                @Override
                public void onResponse(@NonNull Call<Drink> call, @NonNull Response<Drink> response) {
                    assert response.body() != null;
                    Log.i("Debug", String.valueOf(((Drink)response.body()).getDrinks().get(0)));
                    executor.execute(() -> {
                        Drink.Data drink1 = ((Drink)response.body()).getDrinks().get(0);
                        Pair<Bitmap, Drink.Data> drinkWithImage = new Pair<>(ImageDownloader.downloadImagesByUrl(drink1.getStrDrinkThumb()+"/preview"), drink1);
                        //Background work here
                        handler.post(() -> {
                            drinks.add(drinkWithImage);
                            adapter.notifyItemInserted(Integer.MAX_VALUE);//max value to scroll from top
                        });
                    });

                }

                @Override
                public void onFailure(@NonNull Call<Drink> call, @NonNull Throwable t) {

                }
            });
        }
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
