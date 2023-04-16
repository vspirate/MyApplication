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
import com.example.myapplication.model.Drink;

import java.util.ArrayList;

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder> {
    private final ArrayList<Pair<Bitmap, Drink.Data>> mData;//список даних, які будемо розміщувати в RecyclerView
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // передаємо дані в конструктор
    public DataRecyclerViewAdapter(Context context, ArrayList<Pair<Bitmap, Drink.Data>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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
        holder.imageView.setImageBitmap(mData.get(position).first);
        holder.textName.setText(mData.get(position).second.getStrDrink());
        holder.textReceipt.setText(mData.get(position).second.getStrInstructions());
    }

    // загальна кількість рядків
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // зберігає та використовує view компоненти, коли рядок прокручується (виходить з екрана)
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textName;
        TextView textReceipt;


        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textName = itemView.findViewById(R.id.textName);
            textReceipt = itemView.findViewById(R.id.textReceipt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getBindingAdapterPosition());
        }
    }

    // отримання даних з рядка RecyclerView, за яким клацнули
    Pair<Bitmap, Drink.Data> getItem(int id) {
        return mData.get(id);
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
