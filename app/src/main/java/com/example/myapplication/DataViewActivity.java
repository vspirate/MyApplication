package com.example.myapplication;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.adapter.DataRecyclerViewAdapter;
import com.example.myapplication.model.Drink;
import com.example.myapplication.model.DrinkShort;
import com.example.myapplication.model.DrinksList;
import com.example.myapplication.model.db.DrinkDB;
import com.example.myapplication.model.db.DrinkEntity;
import com.example.myapplication.repo.Repository;
import com.example.myapplication.utils.ImageDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
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

    private Repository repository;
    private ArrayList<Pair<Bitmap, DrinkEntity>> drinks = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            BlankFragment blankFragment=BlankFragment.newInstance("one", null);
            getSupportFragmentManager().beginTransaction().add(R.id.fr_container,blankFragment).commit();
        }


        setContentView(R.layout.activity_data_view);


        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataRecyclerViewAdapter(this, drinks);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        repository=new Repository(this);

        Executors.newSingleThreadExecutor().execute(()->{
            List<Pair<Bitmap, DrinkEntity>> val = repository.getAllDrinks();
            handler.post(() -> {
                val.forEach(d->drinks.add(d));
                adapter.notifyItemInserted(Integer.MAX_VALUE);//max value to scroll from top
            });

        });

    }

    @Override
    public void onItemClick(View view, int position) {
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){

            switchFragment(UUID.randomUUID().toString(), drinks.get(position).second);
        }
    }

    void switchFragment(String name, DrinkEntity entity) {

        Fragment fragment;
        fragment = getSupportFragmentManager().findFragmentByTag(name);
        if (fragment ==null) {
            fragment = BlankFragment.newInstance(name, entity);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_container, fragment, name).commit();
        }
        else {
            Toast.makeText(this,"Не меняем", Toast.LENGTH_SHORT).show();
            Log.i("Main","Не меняем");
        }

    }
}
