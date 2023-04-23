package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.weather.List;
import com.example.myapplication.model.weather.Main;
import com.example.myapplication.model.weather.Model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder> {
    private final java.util.List<Main> mData;//список даних, які будемо розміщувати в RecyclerView
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // передаємо дані в конструктор
    public DataRecyclerViewAdapter(Context context, Model data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data.getList().stream().map(List::getMain).collect(Collectors.toList());
    }

    // “створює(надуває)” рядок(пункт) RecyclerView з xml файлу
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_data_item, parent, false);
        return new ViewHolder(view);
    }

    // заповнює кожен рядок RecyclerView даними
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = String.valueOf(mData.get(position));
        holder.textTemp.setText(String.valueOf(mData.get(position).getTemp()));
        holder.textHum.setText(String.valueOf(mData.get(position).getHumidity()));
    }

    // загальна кількість рядків
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // зберігає та використовує view компоненти, коли рядок прокручується (виходить з екрана)
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textTemp;
        TextView textHum;


        ViewHolder(View itemView) {
            super(itemView);
            textTemp = itemView.findViewById(R.id.textTemp);
            textHum = itemView.findViewById(R.id.textHum);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getBindingAdapterPosition());
        }
    }


    // додавання можливості перехата натискання на кнопку
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //  Activity буде реалізовувати цей метод, клацання по елементу
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
