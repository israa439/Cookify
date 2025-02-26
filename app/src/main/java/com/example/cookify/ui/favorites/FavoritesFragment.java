package com.example.cookify.ui.favorites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookify.DataSrc.Adapters.MealAdapter;
import com.example.cookify.DataSrc.Data_structure.userMeal;
import com.example.cookify.DataSrc.Endpoints.fav_recipes;

import com.example.cookify.R;

import com.example.cookify.databinding.FragmentFavoritesBinding;
import com.example.cookify.detailedRecipe;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fav_recipes favRecipes = new fav_recipes(root.getContext());

        recyclerView = root.findViewById(R.id.ur_recipe_container);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SharedPreferences userPrefs = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        int userId = userPrefs.getInt("userId", -1);
        List<userMeal> usermeals= favRecipes.getAllFavoriteMeals(userId);

        MealAdapter mealAdapter = new MealAdapter(getContext(), usermeals, (position, meal) -> {
            System.out.println("entered the meal adapter");
            SharedPreferences sharedPreferences = requireContext().getSharedPreferences("RecipePrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("recipeName", meal.getMealName());
            editor.putString("recipeImage", meal.getMealImage());
            editor.putInt("recipeId", meal.getMealId());
            editor.putInt("recipeDuration", meal.getMealDuration());
            editor.putInt("recipeCals", meal.getMealCalories());
            editor.putString("recipePrepWay", meal.getMealPrepWay() );
            editor.putString("recipeIngredients", meal.getMealIngredients());

            editor.apply();

            System.out.println("Navigating to detailedRecipe with meal: " + meal.getMealName());
            Intent intent = new Intent(getContext(), detailedRecipe.class);
            startActivity(intent);

        });

        recyclerView.setAdapter(mealAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}