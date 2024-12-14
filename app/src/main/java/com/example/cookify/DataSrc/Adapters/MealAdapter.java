package com.example.cookify.DataSrc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookify.DataSrc.Data_structure.Meal;
import com.example.cookify.DataSrc.Data_structure.MealBase;
import com.example.cookify.R;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private Context context;
    private List<? extends MealBase> mealList;
    private OnRecipeClickListener listener;

    public MealAdapter(Context context, List<? extends MealBase> mealList, OnRecipeClickListener listener) {
        this.context = context;
        this.mealList = mealList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipes, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealBase meal = mealList.get(position);
        holder.bind(meal, position, listener);


        holder.mealName.setText(meal.getMealName());


        Glide.with(context)
                .load(meal.getMealImage())
                .into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        ImageView mealImage;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.mealName);
            mealImage = itemView.findViewById(R.id.mealImage);
        }
        public void bind(MealBase meal, int position, OnRecipeClickListener listener) {
            mealName.setText(meal.getMealName());


            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRecipeClick(position, meal);
                }
            });
        }

    }

    // Interface for passing click events
    public interface OnRecipeClickListener {
        void onRecipeClick(int position, MealBase meal);
    }
}
